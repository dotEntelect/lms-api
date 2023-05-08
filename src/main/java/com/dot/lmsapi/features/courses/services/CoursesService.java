package com.dot.lmsapi.features.courses.services;

import com.dot.lmsapi.features.auth.services.AuthService;
import com.dot.lmsapi.features.courses.models.*;
import com.dot.lmsapi.features.courses.repositories.CourseRepository;
import com.dot.lmsapi.features.manageUsers.models.User;
import com.dot.lmsapi.features.manageUsers.models.UserResponseDto;
import com.dot.lmsapi.features.manageUsers.respositories.ManageUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoursesService {
    private final CourseRepository courseRepository;
    private final ManageUsersRepository usersRepository;
    private final AuthService authService;

    public List<CourseResponseDto> getAllCourses() {
        List<CourseResponseDto> courses = new ArrayList<>();
        courseRepository.findAll().forEach(course -> courses.add(toCourseResponseDto(course)));
        return courses;
    }

    public CourseResponseDto getCourseById(Long id) throws Exception {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course Not Found"));
        return toCourseResponseDto(course);
    }

    public CourseResponseDto addCourse(CourseRequestDto req) {
        Course course = courseRepository.save(
                Course.builder()
                        .addedBy(authService.getLoggedInUser().orElseThrow(() ->
                                new NotFoundException("No Logged In User Found")).getId())
                        .code(req.getCode())
                        .description(req.getDescription())
                        .title(req.getTitle())
                        .build()
        );
        return toCourseResponseDto(course);
    }

    public CourseResponseDto updateCourse(Long id, CourseUpdateRequestDto req) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course Not Found"));
        course.setCode(req.getCode());
        course.setDescription(req.getDescription());
        course.setTitle(req.getTitle());
        courseRepository.save(course);
        return toCourseResponseDto(course);
    }

    public CourseResponseDto toggleInstructorLink(CourseLinkToggleDto req) {
        var course = courseRepository.findById(req.getCourseId()).orElseThrow();
        var instructor = usersRepository.findById(req.getInstructorId()).orElseThrow();
        if (req.isLinked()) {
            course.getInstructors().add(instructor);
        } else {
            course.getInstructors().remove(instructor);
        }
        courseRepository.save(course);
        return toCourseResponseDto(course);
    }

    public CourseResponseDto toggleStudentLink(CourseLinkToggleDto req) {
        var course = courseRepository.findById(req.getCourseId())
                .orElseThrow(() -> new NotFoundException(String.format("Course (%s) Not Found", req.getCourseId())));
        var student = usersRepository.findById(req.getStudentId())
                .orElseThrow(() -> new NotFoundException(String.format("Student (%s) Not Found", req.getStudentId())));
        if (req.isLinked()) {
            course.getStudents().add(student);
        } else {
            course.getStudents().remove(student);
        }
        courseRepository.save(course);
        return toCourseResponseDto(course);
    }

    private CourseResponseDto toCourseResponseDto(Course course) {
        User user = usersRepository.findById(course.getAddedBy())
                .orElseThrow(() -> new NotFoundException("User Not Found"));
        List<UserResponseDto> students = new ArrayList<>();
        List<UserResponseDto> instructors = new ArrayList<>();
        if (course.getInstructors() != null) {

            course.getStudents().forEach(student -> students
                    .add(new UserResponseDto(student.getId(), student.getEmailAddress(), student.getFirstName(),
                            student.getLastName(), student.getCellPhone(), student.getRole())));
        }
        if (course.getStudents() != null) {
            course.getInstructors().forEach(instructor -> instructors
                    .add(new UserResponseDto(instructor.getId(), instructor.getEmailAddress(), instructor.getFirstName(),
                            instructor.getLastName(), instructor.getCellPhone(), instructor.getRole())));
        }


        return CourseResponseDto
                .builder()
                .id(course.getId())
                .addedBy(user.getFirstName() + " " + user.getLastName())
                .code(course.getCode())
                .title(course.getTitle())
                .description(course.getDescription())
                .students(students)
                .instructors(instructors)
                .build();
    }
}

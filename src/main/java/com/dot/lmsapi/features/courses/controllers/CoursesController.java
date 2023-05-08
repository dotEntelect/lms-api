package com.dot.lmsapi.features.courses.controllers;

import com.dot.lmsapi.features.courses.models.CourseLinkToggleDto;
import com.dot.lmsapi.features.courses.models.CourseRequestDto;
import com.dot.lmsapi.features.courses.models.CourseResponseDto;
import com.dot.lmsapi.features.courses.models.CourseUpdateRequestDto;
import com.dot.lmsapi.features.courses.services.CoursesService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CoursesController {
    private  final CoursesService coursesService;

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        return ResponseEntity.ok(coursesService.getAllCourses());
    }
    @GetMapping("{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Course Not Found"),
            @ApiResponse(responseCode = "500", description = "An unexpected error has occurred")
    })
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(coursesService.getCourseById(id));
    }
    @PostMapping
    public ResponseEntity<CourseResponseDto> addCourse(@RequestBody CourseRequestDto req) {
        return ResponseEntity.ok(coursesService.addCourse(req));
    }
    @PutMapping("{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable("id") Long id, @RequestBody CourseUpdateRequestDto req) {
        return ResponseEntity.ok(coursesService.updateCourse(id, req));
    }
    @PostMapping("/toggleInstructorLink")
    public ResponseEntity<CourseResponseDto> toggleInstructorLink(@RequestBody CourseLinkToggleDto req) {
        return ResponseEntity.ok(coursesService.toggleInstructorLink(req));
    }
    @PostMapping("/toggleStudentLink")
    public ResponseEntity<CourseResponseDto> toggleStudentLink(@RequestBody CourseLinkToggleDto req) {
        return ResponseEntity.ok(coursesService.toggleStudentLink(req));
    }
}

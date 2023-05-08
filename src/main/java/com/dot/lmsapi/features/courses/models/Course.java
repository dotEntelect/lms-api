package com.dot.lmsapi.features.courses.models;

import com.dot.lmsapi.features.chapters.models.Chapter;
import com.dot.lmsapi.features.lessons.models.Lesson;
import com.dot.lmsapi.features.manageUsers.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String title;
    @Column(name = "description", columnDefinition = "nvarchar(max)")
    private String description;
    private Long addedBy;

    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<User> students = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "instructor_courses",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<User> instructors = new ArrayList<>();


    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "course", cascade = CascadeType.MERGE)
    private List<Chapter> chapters = new ArrayList<>();

}

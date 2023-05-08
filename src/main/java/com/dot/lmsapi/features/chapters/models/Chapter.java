package com.dot.lmsapi.features.chapters.models;

import com.dot.lmsapi.features.courses.models.Course;
import com.dot.lmsapi.features.lessons.models.Lesson;
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
@Table(name = "_chapters")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(name = "description", columnDefinition = "nvarchar(max)")
    private String description;
    private Long addedBy;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "chapter", cascade = CascadeType.MERGE)
    private List<Lesson> lessons = new ArrayList<>();
}

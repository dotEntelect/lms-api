package com.dot.lmsapi.features.lessons.models;

import com.dot.lmsapi.features.chapters.models.Chapter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "description", columnDefinition = "nvarchar(max)")
    private String Description;
    private Long addedBy;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;
}

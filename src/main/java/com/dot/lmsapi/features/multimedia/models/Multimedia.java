package com.dot.lmsapi.features.multimedia.models;

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
@Table(name="_multi_media")
public class Multimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String url;
    private Long addedBy;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Chapter chapter;
}

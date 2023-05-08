package com.dot.lmsapi.features.chapters.models;

import lombok.Data;

@Data
public class ChapterRequestDto {
    private Long id;
    private String title;
    private String description;
    private Long courseId;
}

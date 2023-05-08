package com.dot.lmsapi.features.chapters.models;

import com.dot.lmsapi.features.courses.models.Course;
import lombok.Builder;


@Builder
public record ChapterResponseDto(Long id,
                                 String title,
                                 String description,
                                 String addedBy,
                                 Long courseId) {
}

package com.dot.lmsapi.features.courses.models;

import com.dot.lmsapi.features.chapters.models.Chapter;
import com.dot.lmsapi.features.chapters.models.ChapterResponseDto;
import com.dot.lmsapi.features.manageUsers.models.UserResponseDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Builder
public record CourseResponseDto(Long id,
                                String code,
                                String title,
                                String description,
                                String addedBy, List<UserResponseDto> students,
                                List<UserResponseDto> instructors,
                                List<ChapterResponseDto> chapters) {
}

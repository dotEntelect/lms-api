package com.dot.lmsapi.features.courses.models;

import com.dot.lmsapi.features.chapters.models.ChapterResponseDto;
import com.dot.lmsapi.features.manageUsers.models.UserResponseDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CourseUpdateRequestDto {
    private Long id;
    private String code;
    private String title;
    private String description;
    private List<UserResponseDto> students;
    private List<UserResponseDto> instructors;
    private List<ChapterResponseDto> chapters;
}

package com.dot.lmsapi.features.courses.models;

import lombok.Data;

@Data
public class CourseRequestDto {
    private String code;
    private String title;
    private String description;
}

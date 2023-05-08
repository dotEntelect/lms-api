package com.dot.lmsapi.features.courses.models;

import lombok.Data;

@Data
public class CourseLinkToggleDto {
 private Long courseId;
 private Long instructorId;
 private Long studentId;
 private boolean linked;
}

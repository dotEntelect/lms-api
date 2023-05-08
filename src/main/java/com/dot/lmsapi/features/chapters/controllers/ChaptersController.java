package com.dot.lmsapi.features.chapters.controllers;

import com.dot.lmsapi.features.chapters.models.ChapterRequestDto;
import com.dot.lmsapi.features.chapters.models.ChapterResponseDto;
import com.dot.lmsapi.features.chapters.services.ChapterService;
import com.dot.lmsapi.features.courses.models.CourseRequestDto;
import com.dot.lmsapi.features.courses.models.CourseResponseDto;
import com.dot.lmsapi.features.courses.models.CourseUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chapters")
public class ChaptersController {
    private final ChapterService chapterService;

    @GetMapping("/chaptersByCourse/{courseId}")
    public ResponseEntity<List<ChapterResponseDto>> getChaptersByCourseId(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok(chapterService.getChaptersByCourse(courseId));
    }

    @GetMapping("{id}")
    public ResponseEntity<ChapterResponseDto> getChaptersById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(chapterService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ChapterResponseDto> addCourse(@RequestBody ChapterRequestDto req) {
        return ResponseEntity.ok(chapterService.addChapter(req));
    }

    @PutMapping("{id}")
    public ResponseEntity<ChapterResponseDto> updateCourse(@PathVariable("id") Long id, @RequestBody ChapterRequestDto req) {
        return ResponseEntity.ok(chapterService.updateChapter(id, req));
    }
}

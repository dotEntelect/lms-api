package com.dot.lmsapi.features.chapters.services;

import com.dot.lmsapi.features.auth.services.AuthService;
import com.dot.lmsapi.features.chapters.models.Chapter;
import com.dot.lmsapi.features.chapters.models.ChapterRequestDto;
import com.dot.lmsapi.features.chapters.models.ChapterResponseDto;
import com.dot.lmsapi.features.chapters.repositories.ChaptersRepository;
import com.dot.lmsapi.features.courses.models.Course;
import com.dot.lmsapi.features.courses.models.CourseResponseDto;
import com.dot.lmsapi.features.manageUsers.models.User;
import com.dot.lmsapi.features.manageUsers.respositories.ManageUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterService {
    private final ChaptersRepository chaptersRepository;
    private final ManageUsersRepository usersRepository;
    private final AuthService authService;

    public List<ChapterResponseDto> getChaptersByCourse(Long courseId) {
        List<Chapter> _chapters = new ArrayList<>();
        chaptersRepository.findAllByCourseId(courseId).forEach(_chapters::add);

        List<ChapterResponseDto> chapters = new ArrayList<>();
        _chapters.forEach(chapter -> chapters.add(toChapterResponseDto(chapter)));

        return chapters;
    }

    public ChapterResponseDto addChapter(ChapterRequestDto req) {
        var chapter = chaptersRepository.save(Chapter.builder()
                .course(Course.builder().id(req.getCourseId()).build())
                .addedBy(authService.getLoggedInUser().orElseThrow(() ->
                        new NotFoundException("No Logged In User Found")).getId())
                .title(req.getTitle())
                .description(req.getDescription())
                .build());
        return toChapterResponseDto(chapter);
    }


    public ChapterResponseDto updateChapter(Long id, ChapterRequestDto req) {

        var chapter = chaptersRepository.findById(id).orElseThrow(() -> new NotFoundException("Chapter Not Found"));
        chapter.setDescription(req.getDescription());
        chapter.setTitle(req.getTitle());
        chaptersRepository.save(chapter);
        return toChapterResponseDto(chapter);
    }

    public ChapterResponseDto findById(Long id) {
        var chapter = chaptersRepository.findById(id).orElseThrow(() -> new NotFoundException("Chapter Not Found"));
        return toChapterResponseDto(chapter);
    }

    private ChapterResponseDto toChapterResponseDto(Chapter chapter) {
        User user = usersRepository.findById(chapter.getAddedBy())
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        return ChapterResponseDto
                .builder()
                .id(chapter.getId())
                .addedBy(user.getFirstName() + " " + user.getLastName())
                .courseId(chapter.getCourse().getId())
                .title(chapter.getTitle())
                .description(chapter.getDescription())
                // todo: more properties
                .build();
    }

}

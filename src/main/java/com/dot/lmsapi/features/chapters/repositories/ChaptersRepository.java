package com.dot.lmsapi.features.chapters.repositories;

import com.dot.lmsapi.features.chapters.models.Chapter;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChaptersRepository extends CrudRepository<Chapter, Long>, JpaSpecificationExecutor<Chapter> {
    @Query("select c from Chapter c where c.course.id = :id")
    Iterable<Chapter> findAllByCourseId(Long id);

}

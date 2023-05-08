package com.dot.lmsapi.features.courses.repositories;

import com.dot.lmsapi.features.courses.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}

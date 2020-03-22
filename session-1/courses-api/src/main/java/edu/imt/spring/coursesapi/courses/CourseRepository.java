package edu.imt.spring.coursesapi.courses;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CourseRepository extends CrudRepository<Course, String> {
    Collection<Course> findByTopicId(String topicId);

    Collection<Object> findByTopicIdAndId(String topicId, String id);
}

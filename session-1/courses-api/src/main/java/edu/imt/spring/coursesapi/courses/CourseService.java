package edu.imt.spring.coursesapi.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Collection<Course> getAllCourses(String topicId) {
        return (Collection<Course>) courseRepository.findByTopicId(topicId);
    }

    public Course getCourse(String topicId, String id) {
        return (Course) courseRepository.findByTopicIdAndId(topicId, id).stream().findFirst().get();
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Course course) {
        courseRepository.save(course);
    }
}

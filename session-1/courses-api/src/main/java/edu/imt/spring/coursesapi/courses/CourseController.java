package edu.imt.spring.coursesapi.courses;

import edu.imt.spring.coursesapi.topics.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/topics/{topicId}/courses")
    public Collection<Course> getAllCourses(@PathVariable String topicId) {
        return courseService.getAllCourses(topicId);
    }

    @RequestMapping("/topics/{topicId}/courses/{id}")
    public Course getCourse(@PathVariable String topicId, @PathVariable String id) {
        return courseService.getCourse(topicId, id);
    }

    @RequestMapping(path = "/topics/{topicId}/courses/{id}", method= RequestMethod.DELETE)
    public void deleteCourse(@PathVariable String topicId, @PathVariable String id) {
        courseService.deleteCourse(id);
    }

    @RequestMapping(path = "/topics/{topicId}/courses", method = RequestMethod.PUT)
    public void addCourse(@PathVariable String topicId, @RequestBody Course course) {
        Topic topic = new Topic(topicId, "", "");
        course.setTopic(topic);
        courseService.addCourse(course);
    }

    @RequestMapping(path = "/topics/{topicId}/courses", method = RequestMethod.POST)
    public void updateCourse(@PathVariable String topicId, @RequestBody Course course) {
        Topic topic = new Topic(topicId, "", "");
        course.setTopic(topic);
        courseService.updateCourse(course);
    }
}

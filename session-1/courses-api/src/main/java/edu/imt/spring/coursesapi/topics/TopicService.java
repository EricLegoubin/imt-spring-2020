package edu.imt.spring.coursesapi.topics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Collection<Topic> getAllTopics() {
        return (Collection<Topic>) topicRepository.findAll();
    }

    public Topic getTopic(String id) {
        return topicRepository.findById(id).get();
    }

    public void deleteTopic(String id) {
        topicRepository.deleteById(id);
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public void updateTopic(Topic topic) {
        topicRepository.save(topic);
    }
}

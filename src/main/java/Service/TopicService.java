package Service;

import DTO.TopicDTO;

import java.util.List;

public interface TopicService {
    TopicDTO createTopic(TopicDTO dto);
    List<TopicDTO> getAllTopics();
}

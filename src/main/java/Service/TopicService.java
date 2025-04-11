package Service;

import DTO.TopicDTO;
import aj.org.objectweb.asm.commons.Remapper;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    TopicDTO createTopic(TopicDTO dto);
    List<TopicDTO> getAllTopics();

    Optional<TopicDTO> getTopicById(Long id);

    TopicDTO updateTopic(Long id, TopicDTO topicDTO);

    void deleteTopic(Long id);
}

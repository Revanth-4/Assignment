package ServiceImpl;

import DTO.TopicDTO;
import Entity.Topic;
import Mapper.TopicMapper;
import Repository.TopicRepository;
import Service.TopicService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public TopicDTO createTopic(TopicDTO dto) {
        Topic topic = TopicMapper.dtoToTopic(dto);
        return TopicMapper.topicToDTO(topicRepository.save(topic));
    }

    @Override
    public List<TopicDTO> getAllTopics() {
        return topicRepository.findAll()
                .stream()
                .map(TopicMapper::topicToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TopicDTO> getTopicById(Long id) {
        return topicRepository.findById(id)
                .map(TopicMapper::topicToDTO);
    }

    @Override
    public TopicDTO updateTopic(Long id, TopicDTO topicDTO) {
        Topic existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found with ID: " + id));

        // Update the entity fields
        existingTopic.setName(topicDTO.getName());
        existingTopic.setDescription(topicDTO.getDescription());

        // Save the updated entity
        Topic updatedTopic = topicRepository.save(existingTopic);

        // Convert to DTO and return
        return TopicMapper.topicToDTO(updatedTopic);
    }

    @Override
    public void deleteTopic(Long id) {
        if (!topicRepository.existsById(id)) {
            throw new RuntimeException("Topic not found with ID: " + id);
        }
        topicRepository.deleteById(id);
    }

}

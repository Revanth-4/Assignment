package ServiceImpl;

import DTO.TopicDTO;
import Entity.Topic;
import Mapper.TopicMapper;
import Repository.TopicRepository;
import Service.TopicService;

import java.util.List;
import java.util.stream.Collectors;

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
}

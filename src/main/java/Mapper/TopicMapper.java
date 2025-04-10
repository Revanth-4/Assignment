package Mapper;

import DTO.TopicDTO;
import Entity.Topic;

public class TopicMapper {

    // Entity → DTO
    public static TopicDTO topicToDTO(Topic topic) {
        if (topic == null) return null;

        TopicDTO dto = new TopicDTO();
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setDescription(topic.getDescription());
        return dto;
    }

    // DTO → Entity
    public static Topic dtoToTopic(TopicDTO dto) {
        if (dto == null) return null;

        Topic topic = new Topic();
        topic.setId(dto.getId());
        topic.setName(dto.getName());
        topic.setDescription(dto.getDescription());
        return topic;
    }
}

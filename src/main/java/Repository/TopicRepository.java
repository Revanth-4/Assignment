package Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

	@Query("SELECT t FROM Topic t WHERE t.course.id = :courseId")
    List<Topic> getTopicsByCourseId(Long courseId);

	@Query("SELECT t FROM Topic t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Topic> findByNameContainingIgnoreCase(String keyword);

    @Query("SELECT t FROM Topic t WHERE t.name IN :names")
    Set<Topic> findAllByNameIn(List<String> topics);
}

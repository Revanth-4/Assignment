package Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	// 🔍 1. Find course by name (exact match)
    @Query("SELECT c FROM Course c WHERE c.name = :name")
    Optional<Course> getCourseByName(@Param("name") String name);

    // 🔍 2. Search courses by name (partial match, case-insensitive)
    @Query("SELECT c FROM Course c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Course> searchCoursesByName(@Param("name") String name);

    // 🎯 3. Find courses by type (e.g., "MANDATORY", "ELECTIVE")
    @Query("SELECT c FROM Course c WHERE c.courseType = :courseType")
    List<Course> getCoursesByType(@Param("courseType") String courseType);

    // 📚 4. Find courses by topic name
    @Query("SELECT c FROM Course c JOIN c.topics t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :topicName, '%'))")
    List<Course> getCoursesByTopicName(@Param("topicName") String topicName);

    // 👨‍🎓 5. Get all courses assigned to a specific student
    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId")
    List<Course> getCoursesByStudentId(@Param("studentId") Long studentId);
}

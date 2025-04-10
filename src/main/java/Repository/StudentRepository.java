package Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	// 🔍 1. Exact match: Get students by name
    @Query("SELECT s FROM Student s WHERE s.name = :name")
    List<Student> getStudentsByName(@Param("name") String name);

    // 🔍 2. Partial, case-insensitive match
    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Student> searchStudentsByName(@Param("name") String name);

    // 🔐 3. Verify student by studentCode and DOB
    @Query("SELECT s FROM Student s WHERE s.studentCode = :studentCode AND s.dateOfBirth = :dob")
    Optional<Student> verifyStudent(@Param("studentCode") String studentCode, @Param("dob") LocalDate dob);

    // 📚 4. Get students assigned to a specific course
    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId")
    List<Student> getStudentsByCourseId(@Param("courseId") Long courseId);

    // ✅ 5. Get student by student code
    @Query("SELECT s FROM Student s WHERE s.studentCode = :studentCode")
    Optional<Student> getStudentByStudentCode(@Param("studentCode") String studentCode);
}
package Service;

import DTO.CourseDTO;
import DTO.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    StudentDTO addStudent(StudentDTO studentDTO);
    List<StudentDTO> findStudentsByName(String name);
    void assignCourse(Long studentId, Long courseId);
    void leaveCourse(Long studentId, Long courseId);
    List<CourseDTO> getCoursesForStudent(Long studentId);

    List<StudentDTO> getAllStudents();

    Optional<StudentDTO> getStudentById(Long id);

    StudentDTO updateStudent(Long id, StudentDTO studentDTO);

    void deleteStudent(Long id);

    List<StudentDTO> getStudentsByCourse(Long courseId);

    void removeCourseFromStudent(Long studentId, Long courseId);
}

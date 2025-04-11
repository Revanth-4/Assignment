package Service;

import DTO.CourseDTO;
import DTO.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    CourseDTO addCourse(CourseDTO dto);
    List<CourseDTO> getAllCourses();
    List<StudentDTO> getStudentsByCourseId(Long courseId);
    Optional<CourseDTO> getCourseById(Long id);
    CourseDTO updateCourse(Long id, CourseDTO courseDTO);
    void deleteCourse(Long id);
    void assignCourseToStudent(Long courseId, Long studentId);
}

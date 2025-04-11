package ServiceImpl;

import DTO.CourseDTO;
import DTO.StudentDTO;
import Entity.Course;
import Entity.Student;
import Mapper.CourseMapper;
import Mapper.StudentMapper;
import Repository.CourseRepository;
import Repository.StudentRepository;
import Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public StudentDTO addStudent(StudentDTO dto) {
        Student student = StudentMapper.dtoToStudent(dto);
        return StudentMapper.studentToDTO(studentRepository.save(student));
    }

    @Override
    public List<StudentDTO> findStudentsByName(String name) {
        return studentRepository.searchStudentsByName(name)
                .stream()
                .map(StudentMapper::studentToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void assignCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        student.getCourses().add(course);
        studentRepository.save(student);
    }

    @Override
    public void leaveCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        student.getCourses().removeIf(course -> course.getId().equals(courseId));
        studentRepository.save(student);
    }

    @Override
    public List<CourseDTO> getCoursesForStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        return student.getCourses()
                .stream()
                .map(CourseMapper::courseToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(StudentMapper::studentToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDTO> getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(StudentMapper::studentToDTO);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        return studentRepository.findById(id)
                .map(existing -> {
                    existing.setEmail(studentDTO.getEmail());
                    existing.setMobileNumber(studentDTO.getMobileNumber());
                    existing.setFatherName(studentDTO.getFatherName());
                    existing.setMotherName(studentDTO.getMotherName());
                    // optionally update address if provided
                    return StudentMapper.studentToDTO(studentRepository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDTO> getStudentsByCourse(Long courseId) {
        List<Student> students = studentRepository.getStudentsByCourseId(courseId);
        return students.stream()
                .map(StudentMapper::studentToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void removeCourseFromStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().remove(course);
        studentRepository.save(student);
    }

}


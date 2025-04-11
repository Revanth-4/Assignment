package ServiceImpl;

import DTO.CourseDTO;
import DTO.StudentDTO;
import Entity.Course;
import Entity.Student;
import Entity.Topic;
import Mapper.CourseMapper;
import Mapper.StudentMapper;
import Repository.CourseRepository;
import Repository.StudentRepository;
import Repository.TopicRepository;
import Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public CourseDTO addCourse(CourseDTO dto) {
        Course course = CourseMapper.dtoToCourse(dto);
        return CourseMapper.courseToDTO(courseRepository.save(course));
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::courseToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentsByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        return course.getStudents()
                .stream()
                .map(StudentMapper::studentToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseDTO> getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(CourseMapper::courseToDTO);
    }


    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));

        existingCourse.setCourseName(courseDTO.getCourseName());
        existingCourse.setDescription(courseDTO.getDescription());
        existingCourse.setCourseType(courseDTO.getCourseType());
        existingCourse.setDuration(courseDTO.getDuration());

        // Handle topics if needed (only strings in DTO, assume simple overwrite)


        Set<Topic> topics = topicRepository.findAllByNameIn(courseDTO.getTopics());
        existingCourse.setTopics((List<Topic>) topics);

        Course updatedCourse = courseRepository.save(existingCourse);
        return CourseMapper.courseToDTO(updatedCourse);
    }


    @Override
    public void deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Course not found with ID: " + id);
        }
    }

    @Override
    public void assignCourseToStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course not found with ID: " + courseId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + studentId));

        // Add both sides of the relationship
        course.getStudents().add(student);
        student.getCourses().add(course);

        // Save the owning side
        courseRepository.save(course);
    }
}
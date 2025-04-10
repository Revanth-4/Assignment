package Mapper;

import DTO.CourseDTO;
import Entity.Course;
import Entity.Topic;

import java.util.stream.Collectors;

public class CourseMapper {
    public static CourseDTO courseToDTO(Course course) {
        if (course == null) return null;

        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setCourseName(course.getCourseName());
        dto.setDescription(course.getDescription());
        dto.setCourseType(course.getCourseType());
        dto.setDuration(course.getDuration());

        // Get topic names only
        dto.setTopics(
                course.getTopics()
                        .stream()
                        .map(Topic::getName)
                        .collect(Collectors.toList())
        );

        // Convert students to StudentDTOs
        dto.setStudents(
                course.getStudents()
                        .stream()
                        .map(StudentMapper::studentToDTO)
                        .collect(Collectors.toSet())
        );

        return dto;
    }

    // DTO → Entity
    public static Course dtoToCourse(CourseDTO dto) {
        if (dto == null) return null;

        Course course = new Course();
        course.setId(dto.getId());
        course.setCourseName(dto.getCourseName());
        course.setDescription(dto.getDescription());
        course.setCourseType(dto.getCourseType());
        course.setDuration(dto.getDuration());

        // Topics and students must be set elsewhere (e.g., in service), based on IDs or names
        course.setTopics(null);  // You can set this manually after resolving names to Topic entities
        course.setStudents(null);

        return course;
    }
}

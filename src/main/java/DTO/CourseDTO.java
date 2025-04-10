package DTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Long id;
    private String courseName;
    private String description;
    private String courseType;
    private String duration;
    private List<String> topics;
    private Set<StudentDTO> students = new HashSet<>();
}


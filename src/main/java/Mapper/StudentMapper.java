package Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import DTO.AddressDTO;
import DTO.CourseDTO;
import DTO.StudentDTO;
import Entity.Address;
import Entity.Course;
import Entity.Student;

public class StudentMapper {

    public static StudentDTO studentToDTO(Student student) {
        if (student == null) {
			return null;
		}

        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setDateOfBirth(student.getDateOfBirth());
        dto.setGender(student.getGender());
        dto.setStudentCode(student.getStudentCode());
        dto.setEmail(student.getEmail());
        dto.setMobileNumber(student.getMobileNumber());
        dto.setFatherName(student.getFatherName());
        dto.setMotherName(student.getMotherName());

        // Convert Address list
        List<AddressDTO> addressDTOs = student.getAddresses().stream()
            .map(AddressMapper::addressToDTO)
            .collect(Collectors.toList());
        dto.setAddresses(addressDTOs);

        // Convert Course list
        List<CourseDTO> courseDTOs = student.getCourses().stream()
            .map(CourseMapper::courseToDTO)
            .collect(Collectors.toList());
        dto.setCourses(courseDTOs);

        return dto;
    }

    public static Student dtoToStudent(StudentDTO dto) {
        if (dto == null) {
			return null;
		}

        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setGender(dto.getGender());
        student.setStudentCode(dto.getStudentCode());
        student.setEmail(dto.getEmail());
        student.setMobileNumber(dto.getMobileNumber());
        student.setFatherName(dto.getFatherName());
        student.setMotherName(dto.getMotherName());

        // Convert Address list
        List<Address> addresses = dto.getAddresses().stream()
            .map(AddressMapper::dtoToAddress)
            .collect(Collectors.toList());
        student.setAddresses(addresses);

        // Convert Course list
        List<Course> courses = dto.getCourses().stream()
            .map(CourseMapper::dtoToCourse)
            .collect(Collectors.toList());
        student.setCourses((Set<Course>) courses);

        return student;
    }

    public static List<StudentDTO> studentListToDTOList(List<Student> students) {
        List<StudentDTO> collect = students.stream()
                .map(StudentMapper::studentToDTO)
                .collect(Collectors.toList());
        return collect;
    }

    public static List<Student> dtoListToStudentList(List<StudentDTO> dtos) {
        return dtos.stream()
                .map(StudentMapper::dtoToStudent)
                .collect(Collectors.toList());
    }
}
package DTO;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String studentCode;

    private String email;
    private String mobileNumber;
    private String fatherName;
    private String motherName;

    private List<AddressDTO> addresses;
    private List<CourseDTO> courses;
}

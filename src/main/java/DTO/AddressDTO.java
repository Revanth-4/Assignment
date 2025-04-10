package DTO;

import Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;

    private String type;         // e.g., "Permanent", "Correspondence", "Current"
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String postalCode;
    private StudentDTO student;
}

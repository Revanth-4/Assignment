package Service;

import DTO.AddressDTO;

public interface AddressService {
    AddressDTO addAddressToStudent(Long studentId, AddressDTO dto);
}

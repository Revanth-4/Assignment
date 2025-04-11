package Service;

import DTO.AddressDTO;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    AddressDTO addAddressToStudent(Long studentId, AddressDTO dto);
    AddressDTO createAddress(AddressDTO addressDTO);
    List<AddressDTO> getAllAddresses();
    Optional<AddressDTO> getAddressById(Long id);
    AddressDTO updateAddress(Long id, AddressDTO dto);
    void deleteAddress(Long id);
}

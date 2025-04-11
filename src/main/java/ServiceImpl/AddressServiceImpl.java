package ServiceImpl;

import DTO.AddressDTO;
import Entity.Address;
import Entity.Student;
import Mapper.AddressMapper;
import Repository.AddressRepository;
import Repository.StudentRepository;
import Service.AddressService;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final StudentRepository studentRepository;

    public AddressServiceImpl(AddressRepository addressRepository, StudentRepository studentRepository) {
        this.addressRepository = addressRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public AddressDTO addAddressToStudent(Long studentId, AddressDTO dto) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Address address = AddressMapper.dtoToAddress(dto);
        address.setStudent(student);
        return AddressMapper.addressToDTO(addressRepository.save(address));
    }

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = AddressMapper.dtoToAddress(addressDTO);
        Address saved = addressRepository.save(address);
        return AddressMapper.addressToDTO(saved);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(AddressMapper::addressToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AddressDTO> getAddressById(Long id) {
        return addressRepository.findById(id)
                .map(AddressMapper::addressToDTO);
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressDTO dto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));

        // Update fields from DTO
        address.setType(dto.getType());
        address.setLine1(dto.getLine1());
        address.setLine2(dto.getLine2());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());

        Address updated = addressRepository.save(address);
        return AddressMapper.addressToDTO(updated);
    }

    @Override
    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new RuntimeException("Address not found with id: " + id);
        }
        addressRepository.deleteById(id);
    }

}

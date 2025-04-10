package ServiceImpl;

import DTO.AddressDTO;
import Entity.Address;
import Entity.Student;
import Mapper.AddressMapper;
import Repository.AddressRepository;
import Repository.StudentRepository;
import Service.AddressService;

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
}

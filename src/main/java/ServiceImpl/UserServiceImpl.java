package ServiceImpl;

import DTO.UserDTO;
import Entity.User;
import Mapper.UserMapper;
import Repository.UserRepository;
import Service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.dtoToUser(userDTO);
        user = userRepository.save(user);
        return UserMapper.userToDTO(user);
    }

    @Override
    public Optional<UserDTO> getUserByUsername(String username) {
        return userRepository.getUserByUsername(username)
                .map(UserMapper::userToDTO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::userToDTO)
                .collect(Collectors.toList());
    }
}


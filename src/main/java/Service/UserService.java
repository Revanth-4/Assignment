package Service;

import DTO.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    Optional<UserDTO> getUserByUsername(String username);
    List<UserDTO> getAllUsers();
}

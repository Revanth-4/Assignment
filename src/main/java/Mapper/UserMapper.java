package Mapper;

import DTO.UserDTO;
import Entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    // Convert User entity → UserDTO
    public static UserDTO userToDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());  // optional: mask or skip in some cases
        dto.setEnabled(user.isEnabled());
        dto.setRoles(RoleMapper.roleSetToDTOSet(user.getRoles()));
        return dto;
    }

    // Convert UserDTO → User entity
    public static User dtoToUser(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEnabled(dto.isEnabled());
        user.setRoles(RoleMapper.dtoSetToRoleSet(dto.getRoles()));
        return user;
    }

    // List<User> → List<UserDTO>
    public static List<UserDTO> userListToDTOList(List<User> users) {
        return users.stream()
                .map(UserMapper::userToDTO)
                .collect(Collectors.toList());
    }

    // List<UserDTO> → List<User>
    public static List<User> dtoListToUserList(List<UserDTO> dtos) {
        return dtos.stream()
                .map(UserMapper::dtoToUser)
                .collect(Collectors.toList());
    }
}

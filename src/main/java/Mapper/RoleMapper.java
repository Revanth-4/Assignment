package Mapper;

import DTO.RoleDTO;
import Entity.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class RoleMapper {
    public static Role dtoToRole(RoleDTO dto) {
        if (dto == null) return null;

        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }
    // Role → RoleDTO
    public static RoleDTO roleToDTO(Role role) {
        if (role == null) return null;

        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }
    public static Set<RoleDTO> roleSetToDTOSet(Set<Role> roles) {
        if (roles == null) return null;

        return roles.stream()
                .map(RoleMapper::roleToDTO)
                .collect(Collectors.toSet());
    }

    public static Set<Role> dtoSetToRoleSet(Set<RoleDTO> dtos) {
        if (dtos == null) return null;

        return dtos.stream()
                .map(RoleMapper::dtoToRole)
                .collect(Collectors.toSet());
    }

}

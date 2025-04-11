package Service;

import DTO.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    RoleDTO createRole(RoleDTO dto);
    Optional<RoleDTO> getByName(String name);
    List<RoleDTO> getAllRoles();

    Optional<RoleDTO> getRoleById(Long id);
}

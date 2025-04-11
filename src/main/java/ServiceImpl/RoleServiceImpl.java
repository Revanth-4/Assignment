package ServiceImpl;

import DTO.RoleDTO;
import Entity.Role;
import Mapper.RoleMapper;
import Repository.RoleRepository;
import Service.RoleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO createRole(RoleDTO dto) {
        Role role = RoleMapper.dtoToRole(dto);
        return RoleMapper.roleToDTO(roleRepository.save(role));
    }

    @Override
    public Optional<RoleDTO> getByName(String name) {
        Role role = roleRepository.findByName(name).orElseThrow();
        return Optional.ofNullable(RoleMapper.roleToDTO(role));
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(RoleMapper::roleToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDTO> getRoleById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.map(role -> RoleMapper.roleToDTO(role));
    }

}

package Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Entity.Role;

public interface RoleRepository {

	@Query("SELECT r FROM Role r WHERE r.name = :name")
	Optional<Role> findByName(String name);

	// 🔍 Get all roles for a given user ID
	@Query("SELECT r FROM Role r JOIN r.users u WHERE u.id = :userId")
	List<Role> findRolesByUserId(@Param("userId") Long userId);

    Role save(Role role);

	List<Role> findAll();

	Optional<Role> findById(Long id);
}

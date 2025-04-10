package Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// 🔐 1. Find user by username (for login)
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> getUserByUsername(@Param("username") String username);

    // ✅ 2. Check if a user with the given username exists
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = :username")
    boolean doesUsernameExist(@Param("username") String username);

    // 📧 3. Find user by email
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> getUserByEmail(@Param("email") String email);

    // 👤 4. Get users with a specific role
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<User> getUsersByRoleName(@Param("roleName") String roleName);

    // 🔍 5. Get user by ID and load roles eagerly
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :userId")
    Optional<User> getUserByIdWithRoles(@Param("userId") Long userId);
}

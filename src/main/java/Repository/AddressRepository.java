package Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Entity.Address;

public interface AddressRepository {
	// ✅ JPQL: Get all addresses for a student
    @Query("SELECT a FROM Address a WHERE a.student.id = :studentId")
    List<Address> getAddressesByStudentId(@Param("studentId") Long studentId);

    // ✅ JPQL: Get addresses by student ID and address type
    @Query("SELECT a FROM Address a WHERE a.student.id = :studentId AND a.type = :type")
    List<Address> getAddressesByStudentIdAndType(@Param("studentId") Long studentId, @Param("type") String type);

    // ✅ Optional: Find a specific address by its ID (built-in, but shown here for clarity)
    Optional<Address> findById(Long id);

    // ✅ Optional: Get address by student ID and address ID (e.g., for validating ownership)
    @Query("SELECT a FROM Address a WHERE a.student.id = :studentId AND a.id = :addressId")
    Optional<Address> findByStudentIdAndAddressId(@Param("studentId") Long studentId, @Param("addressId") Long addressId);

    Address save(Address address);

    @Query("SELECT a FROM Address a")
    List<Address> findAll();

    @Query("SELECT COUNT(a) > 0 FROM Address a WHERE a.id = :id")
    boolean existsById(Long id);

    @Query("DELETE FROM Address a WHERE a.id = :id")
    void deleteById(Long id);
}

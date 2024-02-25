package rs.singidunum.carbonfootprints.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.singidunum.carbonfootprints.model.Address;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    @Query("select add from Address add where add.status <> 'ARCHIVED' ")
    List<Address> getAllByNonArchived();

    @Query("SELECT a FROM Address a JOIN a.users u WHERE u.id = :id")
    List<Address> getAllByUsersAddress(@Param(value = "id") Long userId);
}

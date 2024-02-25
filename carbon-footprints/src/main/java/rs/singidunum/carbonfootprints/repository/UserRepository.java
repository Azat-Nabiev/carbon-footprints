package rs.singidunum.carbonfootprints.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.singidunum.carbonfootprints.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}

package rs.singidunum.carbonfootprints.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.singidunum.carbonfootprints.model.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select user from User user where user.status <> 'ARCHIVED'")
    List<User> getAllActive();
}

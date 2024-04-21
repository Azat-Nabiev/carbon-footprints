package rs.singidunum.carbonfootprints.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.singidunum.carbonfootprints.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select user from User user where user.status <> 'ARCHIVED'")
    List<User> getAllActive();

    Optional<User> findByEmail(String email);

    @Query("from User us where us.id = :id")
    Optional<User> findUserById(@Param(value = "id") Integer id);
}

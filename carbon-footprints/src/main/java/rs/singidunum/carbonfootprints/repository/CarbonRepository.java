package rs.singidunum.carbonfootprints.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.singidunum.carbonfootprints.model.Carbon;

import java.util.List;

@Repository
public interface CarbonRepository extends CrudRepository<Carbon, Long> {
    @Query("select crbn from Carbon crbn where crbn.status <> 'ARCHIVED'")
    List<Carbon> getAllActive();

    @Query("select crbn from Carbon crbn where crbn.status <> 'ARCHIVED' and crbn.user.id = :userId")
    List<Carbon> getAllActiveByUserId(@Param(value = "userId") Long userId);
}

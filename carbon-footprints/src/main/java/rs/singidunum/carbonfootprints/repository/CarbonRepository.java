package rs.singidunum.carbonfootprints.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.singidunum.carbonfootprints.model.Carbon;

@Repository
public interface CarbonRepository extends CrudRepository<Carbon, Long> {

}

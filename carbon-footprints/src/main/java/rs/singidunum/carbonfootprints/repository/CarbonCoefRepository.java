package rs.singidunum.carbonfootprints.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.singidunum.carbonfootprints.model.CarbonCoef;

import java.util.List;

@Repository
public interface CarbonCoefRepository extends CrudRepository<CarbonCoef, Long> {

    @Query("select crbnCf from CarbonCoef crbnCf where crbnCf.status <> 'ARCHIVED'")
    List<CarbonCoef> getAllActive();

    @Query("select crbnCf from CarbonCoef  crbnCf where crbnCf.status <> 'ARCHIVED' and crbnCf.user.id = :userId")
    List<CarbonCoef> getAllActiveByUserId(@Param(value = "userId") Long userId);
}

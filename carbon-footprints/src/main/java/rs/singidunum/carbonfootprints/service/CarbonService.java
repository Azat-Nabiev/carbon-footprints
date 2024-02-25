package rs.singidunum.carbonfootprints.service;

import rs.singidunum.carbonfootprints.dto.request.CarbonCoefRequestDto;
import rs.singidunum.carbonfootprints.model.Carbon;

import java.util.List;

public interface CarbonService {
    List<Carbon> getAll();
    List<Carbon> getAllByUserId(Long id);
    Carbon add();
    Carbon edit(Long id, CarbonCoefRequestDto carbonCoefRequestDto);
    Carbon delete(Long id);
}

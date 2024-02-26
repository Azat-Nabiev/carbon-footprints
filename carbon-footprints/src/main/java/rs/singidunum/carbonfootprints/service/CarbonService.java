package rs.singidunum.carbonfootprints.service;

import rs.singidunum.carbonfootprints.dto.request.CarbonCoefRequestDto;
import rs.singidunum.carbonfootprints.dto.request.CarbonRequestDto;
import rs.singidunum.carbonfootprints.model.Carbon;

import java.util.List;

public interface CarbonService {
    List<Carbon> getAll();
    List<Carbon> getAllByUserId(Long id);
    Carbon add(Long userId, CarbonRequestDto carbonRequestDto);
    Carbon edit(Long id, CarbonRequestDto carbonRequestDto);
    Carbon delete(Long id);
}

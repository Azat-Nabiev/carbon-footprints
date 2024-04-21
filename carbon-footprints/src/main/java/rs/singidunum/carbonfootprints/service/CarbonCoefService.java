package rs.singidunum.carbonfootprints.service;

import rs.singidunum.carbonfootprints.controller.dto.request.CarbonCoefRequestDto;
import rs.singidunum.carbonfootprints.model.CarbonCoef;

import java.util.List;

public interface CarbonCoefService {

    List<CarbonCoef> getAllByUserId(Long id);
    List<CarbonCoef> getAll();
    CarbonCoef add(Long userId, CarbonCoefRequestDto carbonCoefRequestDto);
    CarbonCoef edit(Long id, Long userId, CarbonCoefRequestDto carbonCoefRequestDto);
    CarbonCoef delete(Long id);

}

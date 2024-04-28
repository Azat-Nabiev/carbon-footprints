package rs.singidunum.carbonfootprints.service;

import rs.singidunum.carbonfootprints.controller.dto.request.CarbonRequestDto;
import rs.singidunum.carbonfootprints.model.Carbon;
import rs.singidunum.carbonfootprints.controller.dto.ProducedCarbon;

import java.util.List;
import java.util.Set;

public interface CarbonService {
    List<Carbon> getAll();
    List<Carbon> getAllByUserId(Long id);
    Carbon add(Long userId, CarbonRequestDto carbonRequestDto);
    Carbon edit(Long id, CarbonRequestDto carbonRequestDto);
    Carbon delete(Long id);
    ProducedCarbon getProducedCarbon(Long id);
    Set<String> getUsedRecources(Long id);
}

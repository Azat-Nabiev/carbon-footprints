package rs.singidunum.carbonfootprints.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.singidunum.carbonfootprints.dto.request.CarbonCoefRequestDto;
import rs.singidunum.carbonfootprints.model.Carbon;
import rs.singidunum.carbonfootprints.repository.CarbonRepository;
import rs.singidunum.carbonfootprints.service.CarbonService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarbonServiceImpl implements CarbonService {

    private final CarbonRepository carbonRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Carbon> getAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Carbon> getAllByUserId(Long id) {
        return null;
    }

    @Override
    @Transactional
    public Carbon add() {
        return null;
    }

    @Override
    @Transactional
    public Carbon edit(Long id, CarbonCoefRequestDto carbonCoefRequestDto) {
        return null;
    }

    @Override
    @Transactional
    public Carbon delete(Long id) {
        return null;
    }
}

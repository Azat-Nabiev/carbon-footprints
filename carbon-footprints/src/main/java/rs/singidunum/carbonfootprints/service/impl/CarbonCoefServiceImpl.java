package rs.singidunum.carbonfootprints.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.singidunum.carbonfootprints.dto.request.CarbonCoefRequestDto;
import rs.singidunum.carbonfootprints.model.CarbonCoef;
import rs.singidunum.carbonfootprints.repository.CarbonCoefRepository;
import rs.singidunum.carbonfootprints.service.CarbonCoefService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarbonCoefServiceImpl implements CarbonCoefService {

    private final CarbonCoefRepository carbonCoefRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CarbonCoef> getAllByUserId(Long id) {
        return carbonCoefRepository.getAllActiveByUserId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarbonCoef> getAll() {
        return carbonCoefRepository.getAllActive();
    }

    @Override
    @Transactional
    public CarbonCoef add(CarbonCoefRequestDto carbonCoefRequestDto) {
        return null;
    }

    @Override
    @Transactional
    public CarbonCoef edit(Long id, CarbonCoefRequestDto carbonCoefRequestDto) {
        return null;
    }

    @Override
    @Transactional
    public CarbonCoef delete(Long id) {
        return null;
    }
}

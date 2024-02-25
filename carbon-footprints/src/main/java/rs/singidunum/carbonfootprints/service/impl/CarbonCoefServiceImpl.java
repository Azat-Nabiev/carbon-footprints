package rs.singidunum.carbonfootprints.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.singidunum.carbonfootprints.dto.request.CarbonCoefRequestDto;
import rs.singidunum.carbonfootprints.model.CarbonCoef;
import rs.singidunum.carbonfootprints.model.User;
import rs.singidunum.carbonfootprints.model.enums.EntityStatus;
import rs.singidunum.carbonfootprints.repository.CarbonCoefRepository;
import rs.singidunum.carbonfootprints.repository.UserRepository;
import rs.singidunum.carbonfootprints.service.CarbonCoefService;
import rs.singidunum.carbonfootprints.service.mediator.CarbonCoefMediator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarbonCoefServiceImpl implements CarbonCoefService {

    private final CarbonCoefRepository carbonCoefRepository;
    private final UserRepository userRepository;
    private final CarbonCoefMediator carbonCoefMediator;

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
    public CarbonCoef add(Long userId, CarbonCoefRequestDto carbonCoefRequestDto) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() ->
                                          new IllegalStateException(String.format("The user with %s", +userId)));

        CarbonCoef carbonCoef = mapToCarbomCoef(carbonCoefRequestDto);

        carbonCoef.setUser(user);
        carbonCoef.setStatus(EntityStatus.ACTIVE);

        carbonCoefRepository.save(carbonCoef);
        return carbonCoef;
    }

    @Override
    @Transactional
    public CarbonCoef edit(Long id, Long userId, CarbonCoefRequestDto carbonCoefRequestDto) {
        CarbonCoef carbonCoef = carbonCoefRepository.findById(id)
                                                    .orElseThrow(() -> new IllegalStateException(String.format(
                                                            "The carbon coef with %s",
                                                            + id)));
        carbonCoef = carbonCoefMediator.mediate(carbonCoefRequestDto, carbonCoef);
        carbonCoefRepository.save(carbonCoef);

        return carbonCoef;
    }

    @Override
    @Transactional
    public CarbonCoef delete(Long id) {
        CarbonCoef carbonCoef = carbonCoefRepository.findById(id)
                                                    .orElseThrow(() -> new IllegalStateException(String.format(
                                                            "The carbon coef with %s",
                                                            + id)));
        carbonCoef.setStatus(EntityStatus.ARCHIVED);
        carbonCoefRepository.save(carbonCoef);
        return carbonCoef;
    }

    private CarbonCoef mapToCarbomCoef(CarbonCoefRequestDto carbonCoefRequestDto) {
        return CarbonCoef.builder()
                         .name(carbonCoefRequestDto.getName())
                         .coef(carbonCoefRequestDto.getCoef())
                         .build();
    }
}

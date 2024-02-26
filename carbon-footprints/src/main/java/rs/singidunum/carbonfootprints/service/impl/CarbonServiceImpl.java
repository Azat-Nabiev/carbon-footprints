package rs.singidunum.carbonfootprints.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.singidunum.carbonfootprints.dto.request.CarbonCoefRequestDto;
import rs.singidunum.carbonfootprints.dto.request.CarbonRequestDto;
import rs.singidunum.carbonfootprints.model.Address;
import rs.singidunum.carbonfootprints.model.Carbon;
import rs.singidunum.carbonfootprints.model.CarbonCoef;
import rs.singidunum.carbonfootprints.model.User;
import rs.singidunum.carbonfootprints.model.enums.EntityStatus;
import rs.singidunum.carbonfootprints.repository.AddressRepository;
import rs.singidunum.carbonfootprints.repository.CarbonCoefRepository;
import rs.singidunum.carbonfootprints.repository.CarbonRepository;
import rs.singidunum.carbonfootprints.repository.UserRepository;
import rs.singidunum.carbonfootprints.service.CarbonService;
import rs.singidunum.carbonfootprints.service.mediator.CarbonMediator;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarbonServiceImpl implements CarbonService {

    private final CarbonRepository carbonRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CarbonCoefRepository carbonCoefRepository;
    private final CarbonMediator carbonMediator;

    @Override
    @Transactional(readOnly = true)
    public List<Carbon> getAll() {
        return carbonRepository.getAllActive();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Carbon> getAllByUserId(Long id) {
        return carbonRepository.getAllActiveByUserId(id);
    }

    @Override
    @Transactional
    public Carbon add(Long userId, CarbonRequestDto carbonRequestDto) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(()
                                          -> new IllegalStateException(String.format(
                                          "Cannot find user with id: %s",
                                          userId
                                  )));

        Address address = addressRepository.findById(carbonRequestDto.getAddressId())
                                           .orElseThrow(() -> new IllegalStateException(String.format(
                                                   "Cannot find user with id: %s",
                                                   carbonRequestDto.getAddressId()
                                           )));

        CarbonCoef carbonCoef = carbonCoefRepository.findById(carbonRequestDto.getCarbonCoefId())
                                                    .orElseThrow(() -> new IllegalStateException(String.format(
                                                            "Cannot find carbon coef by id: %s", carbonRequestDto.getCarbonCoefId())));

        Carbon carbon = mapToCarbon(carbonRequestDto);

        carbon.setAddress(address);
        carbon.setCoef(carbonCoef);
        carbon.setUser(user);
        carbon.setStatus(EntityStatus.ACTIVE);
        carbon.setProduced(countProducedCarbon(carbonCoef.getCoef(), carbon.getAmount()));

        carbonRepository.save(carbon);
        return carbon;
    }

    @Override
    @Transactional
    public Carbon edit(Long id, CarbonRequestDto carbonRequestDto) {
        Carbon carbon = carbonRepository.findById(id)
                                        .orElseThrow(() -> new IllegalStateException(String.format("Cannot find carbon by id: %s", id)));
        carbon = carbonMediator.mediate(carbonRequestDto, carbon);
        carbonRepository.save(carbon);
        return carbon;
    }

    @Override
    @Transactional
    public Carbon delete(Long id) {
        Carbon carbon = carbonRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Cannot find carbon by id: %s", id)));
        carbon.setStatus(EntityStatus.ARCHIVED);
        carbonRepository.save(carbon);
        return carbon;
    }

    private Carbon mapToCarbon(CarbonRequestDto carbonRequestDto) {
        return Carbon.builder()
                     .amount(carbonRequestDto.getAmount())
                     .build();
    }


    private Long countProducedCarbon(Double coef, Long amount) {
        return Math.round(coef * amount);
    }
}

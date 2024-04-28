package rs.singidunum.carbonfootprints.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.singidunum.carbonfootprints.controller.dto.request.CarbonRequestDto;
import rs.singidunum.carbonfootprints.model.Address;
import rs.singidunum.carbonfootprints.model.Carbon;
import rs.singidunum.carbonfootprints.model.CarbonCoef;
import rs.singidunum.carbonfootprints.controller.dto.ProducedCarbon;
import rs.singidunum.carbonfootprints.model.User;
import rs.singidunum.carbonfootprints.model.enums.EntityStatus;
import rs.singidunum.carbonfootprints.repository.AddressRepository;
import rs.singidunum.carbonfootprints.repository.CarbonCoefRepository;
import rs.singidunum.carbonfootprints.repository.CarbonRepository;
import rs.singidunum.carbonfootprints.repository.UserRepository;
import rs.singidunum.carbonfootprints.service.CarbonService;
import rs.singidunum.carbonfootprints.service.mediator.CarbonMediator;
import rs.singidunum.carbonfootprints.util.CarbonUtil;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarbonServiceImpl implements CarbonService {

    private final CarbonRepository carbonRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CarbonCoefRepository carbonCoefRepository;
    private final CarbonMediator carbonMediator;
    private final CarbonUtil carbonUtil;

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
        carbon.setLastUpdated(LocalDateTime.now());
        carbon.setProduced(carbonUtil.countProducedCarbon(carbonCoef, carbon.getAmount()));

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


    @Override
    @Transactional(readOnly = true)
    public ProducedCarbon getProducedCarbon(Long id) {
        List<Carbon> carbons = carbonRepository.getAllActiveByUserId(id);

        return carbonUtil.getAllProducedCarbon(carbons);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> getUsedRecources(Long id) {
        Set<String> usedResources = new HashSet<>();
        List<Carbon> carbons = carbonRepository.getAllActiveByUserId(id);

        for (Carbon carbon : carbons) {
            CarbonCoef carbonCoef = carbonCoefRepository.findById(carbon.getCoef().getId())
                    .orElseThrow(() -> new IllegalStateException("Cannot find carbon coef by id"));
            usedResources.add(carbonCoef.getName());
        }
        return usedResources;
    }
}

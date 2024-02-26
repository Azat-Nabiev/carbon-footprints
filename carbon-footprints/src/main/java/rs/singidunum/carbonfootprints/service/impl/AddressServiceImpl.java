package rs.singidunum.carbonfootprints.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.singidunum.carbonfootprints.dto.request.AddressRequestDto;
import rs.singidunum.carbonfootprints.model.Address;
import rs.singidunum.carbonfootprints.model.enums.EntityStatus;
import rs.singidunum.carbonfootprints.repository.AddressRepository;
import rs.singidunum.carbonfootprints.service.AddressService;
import rs.singidunum.carbonfootprints.service.mediator.AddressMediator;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMediator addressMediator;

    @Override
    @Transactional(readOnly = true)
    public List<Address> getAllByUserId(Long id) {
        return addressRepository.getAllByUsersAddress(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> getAll() {
        return addressRepository.getAllByNonArchived();
    }

    @Override
    @Transactional
    public Address add(AddressRequestDto addressRequestDto) {
        Address address = mapToPm(addressRequestDto);
        address.setStatus(EntityStatus.ACTIVE);
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address edit(Long id, AddressRequestDto addressRequestDto) {
        Address address = addressRepository.findById(id)
                                           .orElseThrow(() ->
                                                   new IllegalStateException(String.format("Cannot find an address by id: %s", id)));
        address = addressMediator.mediate(address);

        addressRepository.save(address);
        return address;
    }

    @Override
    @Transactional
    public Address delete(Long id) {
        Address address = addressRepository.findById(id)
                                           .orElseThrow(() ->
                                                   new IllegalStateException(String.format("Cannot find an address by id: %s", id)));
        address.setStatus(EntityStatus.ARCHIVED);
        addressRepository.save(address);

        return address;
    }

    private Address mapToPm(AddressRequestDto addressRequestDto) {
        return Address.builder()
                .country(addressRequestDto.getCountry())
                .city(addressRequestDto.getCountry())
                .street(addressRequestDto.getStreet())
                .house(addressRequestDto.getHouse())
                .flat(addressRequestDto.getFlat())
                .postalCode(addressRequestDto.getPostalCode())
                      .build();
    }
}

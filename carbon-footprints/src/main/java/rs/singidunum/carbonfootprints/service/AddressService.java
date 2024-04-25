package rs.singidunum.carbonfootprints.service;

import rs.singidunum.carbonfootprints.controller.dto.request.AddressFullRequestDto;
import rs.singidunum.carbonfootprints.controller.dto.request.AddressRequestDto;
import rs.singidunum.carbonfootprints.controller.dto.response.AddressFullResponseDto;
import rs.singidunum.carbonfootprints.model.Address;

import java.util.List;

public interface AddressService {

    List<Address> getCompactAllByUserId(Long id);
    List<AddressFullResponseDto> getFullAllByUserId(Long id);
    List<Address> getAll();
    AddressFullResponseDto add(Long userId, AddressFullRequestDto addressRequestDto);
    Address edit(Long id, AddressRequestDto addressRequestDto);
    Address delete(Long id);
    byte[] getXlsxReport(Long userId);
}

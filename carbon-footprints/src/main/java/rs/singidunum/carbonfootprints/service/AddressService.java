package rs.singidunum.carbonfootprints.service;

import rs.singidunum.carbonfootprints.dto.request.AddressRequestDto;
import rs.singidunum.carbonfootprints.model.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAllByUserId(Long id);
    List<Address> getAll();
    Address add(AddressRequestDto addressRequestDto);
    Address edit(Long id, AddressRequestDto addressRequestDto);
    Address delete(Long id);
}

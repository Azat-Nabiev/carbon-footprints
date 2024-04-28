package rs.singidunum.carbonfootprints.service.mediator;

import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.controller.dto.request.AddressFullRequestDto;
import rs.singidunum.carbonfootprints.controller.dto.request.AddressRequestDto;
import rs.singidunum.carbonfootprints.model.Address;
import rs.singidunum.carbonfootprints.model.Carbon;

@Service
public class AddressMediator {
    public Address mediate(AddressFullRequestDto addressRequestDto, Address address) {
        address.setCountry(addressRequestDto.getCountry());
        address.setCity(addressRequestDto.getCity());
        address.setStreet(addressRequestDto.getStreet());
        address.setHouse(addressRequestDto.getHouse());
        address.setFlat(addressRequestDto.getFlat());
        address.setPostalCode(addressRequestDto.getPostalCode());
        return address;
    }
}

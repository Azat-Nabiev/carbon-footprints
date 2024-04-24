package rs.singidunum.carbonfootprints.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.controller.dto.response.AddressResponseDto;
import rs.singidunum.carbonfootprints.model.Address;

import java.util.List;

@Service
public class AddressMapper {

    public List<AddressResponseDto> mapToResponseDtoList(List<Address> addresses) {
        return addresses.stream().map(this::mapToAddressResponseDto).toList();
    }

    public AddressResponseDto mapToAddressResponseDto(Address address) {
        return AddressResponseDto.builder()
                .id(address.getId())
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .house(address.getHouse())
                .flat(address.getFlat())
                .postalCode(address.getPostalCode())
                .buildingType(address.getBuildingType().toString())
                .status(address.getStatus().toString()).build();
    }
}

package rs.singidunum.carbonfootprints.controller.mapper;

import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.controller.dto.response.AddressСompactResponseDto;
import rs.singidunum.carbonfootprints.model.Address;

import java.util.List;

@Service
public class AddressMapper {

    public List<AddressСompactResponseDto> mapToCompactResponseDtoList(List<Address> addresses) {
        return addresses.stream().map(this::mapToCompactAddressResponseDto).toList();
    }

    public AddressСompactResponseDto mapToCompactAddressResponseDto(Address address) {
        return AddressСompactResponseDto.builder()
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

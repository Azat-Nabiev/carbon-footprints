package rs.singidunum.carbonfootprints.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.controller.dto.response.AddressСompactResponseDto;
import rs.singidunum.carbonfootprints.controller.dto.CarbonCoefDto;
import rs.singidunum.carbonfootprints.controller.dto.response.CarbonResponseDto;
import rs.singidunum.carbonfootprints.model.Address;
import rs.singidunum.carbonfootprints.model.Carbon;
import rs.singidunum.carbonfootprints.model.CarbonCoef;

import java.util.List;

@Service
public class CarbonMapper {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<CarbonResponseDto> mapToCarbonResponseDtoList(List<Carbon> carbonList) {
        return carbonList.stream().map(this::mapToCarbonResponseDto).toList();
    }

    public CarbonResponseDto mapToCarbonResponseDto(Carbon carbon) {
        return CarbonResponseDto.builder()
                .id(carbon.getId())
                .user(userMapper.mapToUserResponseDto(carbon.getUser()))
                .address(mapToCompactAddressResponseDto(carbon.getAddress()))
                .coef(mapToCompactCarbonCoefResponse(carbon.getCoef()))
                .amount(carbon.getAmount())
                .lastUpdated(carbon.getLastUpdated())
                .produced(carbon.getProduced())
                                .build();
    }


    private CarbonCoefDto mapToCompactCarbonCoefResponse(CarbonCoef carbonCoef) {
        return CarbonCoefDto.builder()
                            .id(carbonCoef.getId()).name(carbonCoef.getName()).build();
    }

    private AddressСompactResponseDto mapToCompactAddressResponseDto(Address address) {
        return AddressСompactResponseDto.builder()
                                        .id(address.getId())
                                        .country(address.getCountry())
                                        .city(address.getCity())
                                        .street(address.getStreet())
                                        .house(address.getHouse())
                                        .flat(address.getFlat())
                                        .postalCode(address.getPostalCode())
                                        .buildingType(address.getBuildingType().toString())
                                        .status(address.getStatus().toString())
                                        .build();
    }
}

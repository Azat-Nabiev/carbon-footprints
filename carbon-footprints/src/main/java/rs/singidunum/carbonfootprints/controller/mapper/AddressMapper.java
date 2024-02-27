package rs.singidunum.carbonfootprints.controller.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.dto.response.AddressResponseDto;
import rs.singidunum.carbonfootprints.dto.response.CarbonCoefResponseDto;
import rs.singidunum.carbonfootprints.dto.response.CarbonResponseDto;
import rs.singidunum.carbonfootprints.dto.response.UserResponseDto;
import rs.singidunum.carbonfootprints.model.Address;
import rs.singidunum.carbonfootprints.model.Carbon;
import rs.singidunum.carbonfootprints.model.CarbonCoef;
import rs.singidunum.carbonfootprints.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressMapper {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

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
                .users(address.getUsers() != null ? mapToUsersList(address.getUsers()) : null)
                .buildingType(address.getBuildingType().toString())
                .status(address.getStatus().toString()).build();
    }

    public List<UserResponseDto> mapToUsersList(List<User> users) {
        List<UserResponseDto> usersResponse = new ArrayList<>();

        users.forEach(value -> usersResponse.add(userMapper.mapToUserResponseDto(value)));

        return usersResponse;
    }

    //TODO: create custom carbon mapper
    private List<CarbonResponseDto> mapToCarbonResponseDto(List<Carbon> carbon) {
        List<CarbonResponseDto> carbonResponseDtoList = new ArrayList<>();
        carbon.forEach(value -> carbonResponseDtoList.add(CarbonResponseDto.builder()
                                                             .id(value.getId())
                                                             .amount(value.getAmount())
                                                                           .build()));
        return carbonResponseDtoList;
    }



}

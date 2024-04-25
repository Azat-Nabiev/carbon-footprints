package rs.singidunum.carbonfootprints.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class AddressСompactResponseDto {
    private Long id;
    private String country;
    private String city;
    private String street;
    private String house;
    private String flat;
    private String postalCode;
    private List<UserResponseDto> users;
    private String buildingType;
    private List<CarbonResponseDto> carbon;
    private String status;
}

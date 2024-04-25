package rs.singidunum.carbonfootprints.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.singidunum.carbonfootprints.controller.dto.CarbonCompactDto;

import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressFullResponseDto {
    private Long id;
    private String country;
    private String city;
    private String street;
    private String house;
    private String flat;
    private String postalCode;
    private String buildingType;
    private List<CarbonCompactDto> carbon;
    private String status;
}

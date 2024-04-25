package rs.singidunum.carbonfootprints.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.singidunum.carbonfootprints.controller.dto.CarbonCompactDto;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressFullRequestDto {
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

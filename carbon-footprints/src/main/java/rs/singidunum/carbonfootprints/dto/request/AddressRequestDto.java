package rs.singidunum.carbonfootprints.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.singidunum.carbonfootprints.model.enums.BuildingType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {
    private String country;
    private String city;
    private String street;
    private String house;
    private String flat;
    private String postalCode;
    private BuildingType buildingType;
}

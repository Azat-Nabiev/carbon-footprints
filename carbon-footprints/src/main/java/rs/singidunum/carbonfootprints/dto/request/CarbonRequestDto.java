package rs.singidunum.carbonfootprints.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarbonRequestDto {
    private Long addressId;
    private Long carbonCoefId;
    private Double amount;

    //TONS/KG
    private String weightType;

    //TODO: check project
}

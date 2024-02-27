package rs.singidunum.carbonfootprints.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarbonRequestDto {
    private Long addressId;
    private Long carbonCoefId;
    private Long amount;
}

package rs.singidunum.carbonfootprints.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarbonRequestDto {
    private CarbonCoefRequestDto coef;
    private Long amount;
}

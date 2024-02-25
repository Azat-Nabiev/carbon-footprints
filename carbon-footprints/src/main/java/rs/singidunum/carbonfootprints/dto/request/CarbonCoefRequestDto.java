package rs.singidunum.carbonfootprints.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarbonCoefRequestDto {
    private String name;
    private Double coef;
}

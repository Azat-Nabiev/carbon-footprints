package rs.singidunum.carbonfootprints.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarbonCoefResponseDto {
    private Long id;
    private String name;
    private Double coef;
    private String status;
}

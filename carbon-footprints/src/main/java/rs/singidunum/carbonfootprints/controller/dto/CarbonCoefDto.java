package rs.singidunum.carbonfootprints.controller.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarbonCoefDto {
    private Long id;
    private String name;
    private String status;
}

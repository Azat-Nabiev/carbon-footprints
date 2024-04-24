package rs.singidunum.carbonfootprints.controller.dto.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarbonCoefResponseDto {
    private Long id;
    private String name;

    private String status;
}

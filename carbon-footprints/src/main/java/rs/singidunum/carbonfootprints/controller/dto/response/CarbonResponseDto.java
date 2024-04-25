package rs.singidunum.carbonfootprints.controller.dto.response;

import lombok.Builder;
import lombok.Data;
import rs.singidunum.carbonfootprints.controller.dto.CarbonCoefDto;

import java.time.LocalDateTime;

@Builder
@Data
public class CarbonResponseDto {
    private Long id;
    private UserResponseDto user;
    private AddressСompactResponseDto address;
    private CarbonCoefDto coef;
    private Double amount;
    private LocalDateTime lastUpdated;
    private Double produced;
}

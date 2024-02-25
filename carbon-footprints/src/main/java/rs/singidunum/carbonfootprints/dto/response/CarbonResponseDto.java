package rs.singidunum.carbonfootprints.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CarbonResponseDto {
    private Long id;
    private UserResponseDto user;
    private AddressResponseDto address;
    private CarbonCoefResponseDto coef;
    private Long amount;
    private LocalDateTime lastUpdated;
}

package rs.singidunum.carbonfootprints.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonCompactDto {
    private Long id;
    private Double amount;
    private Double produced;
    private CarbonCoefDto coef;
    private LocalDateTime lastUpd;
}

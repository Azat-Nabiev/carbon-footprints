package rs.singidunum.carbonfootprints.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProducedCarbon {
    private Double produced;
}

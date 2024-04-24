package rs.singidunum.carbonfootprints.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProducedCarbon {
    private Double produced;
}

package rs.singidunum.carbonfootprints.dto.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarbonCoefResponseDto {
    private Long id;
    private String name;
    /**
     * Carbon oxidation coef in fuel (coc) - K1
     */
    @Column(name = "coc")
    private Double coc;
    /**
     * net calorific value (NCV)
     */
    @Column(name = "ncv")
    private Double ncv;
    /**
     * carbon emission factor (CEF) - K2
     */
    @Column(name = "cef")
    private Double cef;

    private String status;
}

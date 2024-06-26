package rs.singidunum.carbonfootprints.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRating {
    private Long id;
    private Long position;
    private String firstName;
    private String lastName;
    private Double produced;
}

package rs.singidunum.carbonfootprints.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRating {
    private Long id;
    private String firstName;
    private String lastName;
    private Double produced;
}

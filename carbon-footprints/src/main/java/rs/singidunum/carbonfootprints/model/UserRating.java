package rs.singidunum.carbonfootprints.model;

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
    private String firstName;
    private String lastName;
    private Double produced;
    private Long usersCurrentPosition;
}

package rs.singidunum.carbonfootprints.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoggedUser {
    private Long loggedUserPosition;
    private Double producedAmount;
    private List<UserRating> usersRating;
}

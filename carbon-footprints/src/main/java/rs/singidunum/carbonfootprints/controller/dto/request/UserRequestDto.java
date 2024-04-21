package rs.singidunum.carbonfootprints.controller.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

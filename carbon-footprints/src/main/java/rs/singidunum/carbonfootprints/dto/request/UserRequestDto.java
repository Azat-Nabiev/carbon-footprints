package rs.singidunum.carbonfootprints.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
    private String name;
    private String lastName;
    private String email;
    private String password;
}

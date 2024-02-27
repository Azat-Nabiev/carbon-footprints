package rs.singidunum.carbonfootprints.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
}

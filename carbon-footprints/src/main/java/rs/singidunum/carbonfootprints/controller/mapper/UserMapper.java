package rs.singidunum.carbonfootprints.controller.mapper;

import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.dto.response.UserResponseDto;
import rs.singidunum.carbonfootprints.model.User;

@Service
public class UserMapper {

    public UserResponseDto mapToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getLastName())
                              .build();
    }

}

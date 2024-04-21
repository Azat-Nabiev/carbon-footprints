package rs.singidunum.carbonfootprints.controller.mapper;

import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.controller.dto.response.UserResponseDto;
import rs.singidunum.carbonfootprints.model.User;

import java.util.List;

@Service
public class UserMapper {

    public UserResponseDto mapToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                              .build();
    }

    public List<UserResponseDto> mapToUserResponseDtoList(List<User> users) {
        return users.stream().map(this::mapToUserResponseDto).toList();
    }

}

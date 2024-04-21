package rs.singidunum.carbonfootprints.service.mediator;

import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.controller.dto.request.UserRequestDto;
import rs.singidunum.carbonfootprints.model.User;

@Service
public class UserMediator {

    public User mediate(UserRequestDto userRequestDto, User user) {
        if (userRequestDto.getFirstName() != null) {
            user.setFirstName(userRequestDto.getFirstName());
        }
        if (userRequestDto.getLastName() != null) {
            user.setLastName(userRequestDto.getLastName());
        }
        return user;
    }

}

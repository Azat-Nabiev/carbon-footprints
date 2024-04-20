package rs.singidunum.carbonfootprints.service.mediator;

import org.springframework.stereotype.Service;
import rs.singidunum.carbonfootprints.dto.request.UserRequestDto;
import rs.singidunum.carbonfootprints.model.User;

@Service
public class UserMediator {

    public User mediate(UserRequestDto userRequestDto, User user) {
        if (userRequestDto.getName() != null) {
            user.setName(userRequestDto.getName());
        }
        if (userRequestDto.getLastName() != null) {
            user.setLastName(userRequestDto.getLastName());
        }
        return user;
    }

}

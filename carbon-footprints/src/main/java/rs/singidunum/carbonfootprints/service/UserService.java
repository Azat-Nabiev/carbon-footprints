package rs.singidunum.carbonfootprints.service;

import rs.singidunum.carbonfootprints.controller.dto.LoggedUser;
import rs.singidunum.carbonfootprints.controller.dto.request.UserRequestDto;
import rs.singidunum.carbonfootprints.model.User;
import rs.singidunum.carbonfootprints.controller.dto.UserRating;

import java.util.List;

public interface UserService {
    LoggedUser getAllSorted(Long userId);
    User getByUserId(Long id);
    User add(UserRequestDto userRequestDto);
    User edit(Long id, UserRequestDto userRequestDto);
    User delete(Long id);
}

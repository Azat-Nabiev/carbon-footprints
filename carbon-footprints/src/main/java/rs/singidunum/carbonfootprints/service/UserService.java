package rs.singidunum.carbonfootprints.service;

import rs.singidunum.carbonfootprints.dto.request.UserRequestDto;
import rs.singidunum.carbonfootprints.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    List<User> getAllByUserId(Long id);
    User add(UserRequestDto userRequestDto);
    User edit(Long id, UserRequestDto userRequestDto);
    User delete(Long id);

}

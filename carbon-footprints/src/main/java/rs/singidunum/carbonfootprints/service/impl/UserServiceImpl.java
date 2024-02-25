package rs.singidunum.carbonfootprints.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.singidunum.carbonfootprints.dto.request.UserRequestDto;
import rs.singidunum.carbonfootprints.model.User;
import rs.singidunum.carbonfootprints.repository.UserRepository;
import rs.singidunum.carbonfootprints.service.UserService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllByUserId(Long id) {
        return null;
    }

    @Override
    @Transactional
    public User add(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    @Transactional
    public User edit(Long id, UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    @Transactional
    public User delete(Long id) {
        return null;
    }
}

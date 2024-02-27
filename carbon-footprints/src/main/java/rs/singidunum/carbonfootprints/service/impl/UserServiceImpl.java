package rs.singidunum.carbonfootprints.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.singidunum.carbonfootprints.dto.request.UserRequestDto;
import rs.singidunum.carbonfootprints.model.User;
import rs.singidunum.carbonfootprints.model.enums.EntityStatus;
import rs.singidunum.carbonfootprints.repository.UserRepository;
import rs.singidunum.carbonfootprints.service.UserService;
import rs.singidunum.carbonfootprints.service.mediator.UserMediator;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMediator userMediator;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.getAllActive();
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUserId(Long id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new IllegalStateException(String.format("Cannot find a user by id: %s", id)));
    }

    @Override
    @Transactional
    public User add(UserRequestDto userRequestDto) {
        User user = mapToUser(userRequestDto);

        userRepository.save(user);

        return user;
    }

    @Override
    @Transactional
    public User edit(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new IllegalStateException(String.format("Cannot find a user by id: %s", id)));
        user = userMediator.mediate(userRequestDto, user);

        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public User delete(Long id) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new IllegalStateException(String.format("Cannot find a user by id: %s", id)));
        user.setStatus(EntityStatus.ARCHIVED);
        return user;
    }

    private User mapToUser(UserRequestDto userRequestDto) {
        LocalDateTime now = LocalDateTime.now();
        return User
                .builder()
                .name(userRequestDto.getName())
                .lastName(userRequestDto.getLastName())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .createdDate(now)
                .status(EntityStatus.ACTIVE).build();
    }
}

package rs.singidunum.carbonfootprints.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.singidunum.carbonfootprints.controller.dto.request.UserRequestDto;
import rs.singidunum.carbonfootprints.model.User;
import rs.singidunum.carbonfootprints.model.UserRating;
import rs.singidunum.carbonfootprints.model.enums.EntityStatus;
import rs.singidunum.carbonfootprints.repository.UserRepository;
import rs.singidunum.carbonfootprints.service.CarbonService;
import rs.singidunum.carbonfootprints.service.UserService;
import rs.singidunum.carbonfootprints.service.mediator.UserMediator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMediator userMediator;
    private final CarbonService carbonService;

    @Override
    @Transactional(readOnly = true)
    public List<UserRating> getAllSorted() {
        List<User> users = userRepository.getAllActive();
        return createUserRating(users);
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
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .createdDate(now)
                .status(EntityStatus.ACTIVE).build();
    }

    private List<UserRating> createUserRating(List<User> users) {
        List<UserRating> userRatings = new ArrayList<>();

        for (User user : users) {
            userRatings.add(new UserRating(user.getId(), user.getFirstName(),
                    user.getLastName(), carbonService.getProducedCarbon(user.getId()).getProduced()));
        }

        return userRatings;
    }
}

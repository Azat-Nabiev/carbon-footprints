package rs.singidunum.carbonfootprints.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.singidunum.carbonfootprints.controller.dto.LoggedUser;
import rs.singidunum.carbonfootprints.controller.dto.request.UserRequestDto;
import rs.singidunum.carbonfootprints.model.User;
import rs.singidunum.carbonfootprints.controller.dto.UserRating;
import rs.singidunum.carbonfootprints.model.enums.EntityStatus;
import rs.singidunum.carbonfootprints.repository.UserRepository;
import rs.singidunum.carbonfootprints.service.CarbonService;
import rs.singidunum.carbonfootprints.service.UserService;
import rs.singidunum.carbonfootprints.service.mediator.UserMediator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMediator userMediator;
    private final CarbonService carbonService;

    @Override
    @Transactional(readOnly = true)
    public LoggedUser getAllSorted(Long userId) {
        List<User> users = userRepository.getAllActive();
        return createUserRating(users, userId);
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

    private LoggedUser createUserRating(List<User> users, Long userId) {
        LoggedUser loggedUser = new LoggedUser();
        List<UserRating> userRatings = new ArrayList<>();
        for (User user : users) {
            UserRating userRating = new UserRating();
            userRating.setId(user.getId());
            userRating.setFirstName(user.getFirstName());
            userRating.setLastName(user.getLastName());
            userRating.setProduced(carbonService.getProducedCarbon(user.getId()).getProduced());
            userRatings.add(userRating);
        }
        userRatings.sort(Comparator.comparingDouble(UserRating::getProduced));

        long counter = 1L;
        for (UserRating userRating : userRatings) {
            userRating.setPosition(counter);
            if (Objects.equals(userId, userRating.getId())) {
                loggedUser.setLoggedUserPosition(counter);
                loggedUser.setProducedAmount(userRating.getProduced());
            }
            counter++;
        }

        loggedUser.setUsersRating(userRatings);
        return loggedUser;
    }
}

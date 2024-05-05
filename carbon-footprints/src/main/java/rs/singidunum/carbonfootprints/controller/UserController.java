package rs.singidunum.carbonfootprints.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.singidunum.carbonfootprints.controller.dto.LoggedUser;
import rs.singidunum.carbonfootprints.controller.dto.response.UserResponseDto;
import rs.singidunum.carbonfootprints.controller.mapper.UserMapper;
import rs.singidunum.carbonfootprints.model.User;
import rs.singidunum.carbonfootprints.controller.dto.UserRating;
import rs.singidunum.carbonfootprints.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/rate/{id}")
    @Operation(summary = "Getting all active user sorted by the produced amount")
    public ResponseEntity<LoggedUser> retrieveAll(@PathVariable(value = "id") Long userId) {
        LoggedUser users = userService.getAllSorted(userId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Getting an user by id")
    public ResponseEntity<UserResponseDto> retrieveById(@PathVariable(name = "id") Long userId) {
        User user = userService.getByUserId(userId);
        return ResponseEntity.ok(userMapper.mapToUserResponseDto(user));
    }
}

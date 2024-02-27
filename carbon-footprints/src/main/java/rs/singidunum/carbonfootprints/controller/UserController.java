package rs.singidunum.carbonfootprints.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.singidunum.carbonfootprints.controller.mapper.UserMapper;
import rs.singidunum.carbonfootprints.dto.request.UserRequestDto;
import rs.singidunum.carbonfootprints.dto.response.UserResponseDto;
import rs.singidunum.carbonfootprints.model.User;
import rs.singidunum.carbonfootprints.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    @Operation(summary = "Register a user")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.add(userRequestDto);
        return ResponseEntity.ok(userMapper.mapToUserResponseDto(user));
    }

    @GetMapping
    @Operation(summary = "Getting all active user")
    public ResponseEntity<List<UserResponseDto>> retrieveAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(userMapper.mapToUserResponseDtoList(users));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Getting an user by id")
    public ResponseEntity<UserResponseDto> retrieveById(@PathVariable(name = "id") Long userId) {
        User user = userService.getByUserId(userId);
        return ResponseEntity.ok(userMapper.mapToUserResponseDto(user));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updating an user by id")
    public ResponseEntity<UserResponseDto> edit(@PathVariable(name = "id") Long id, @RequestBody UserRequestDto userRequestDto) {
        User user = userService.edit(id, userRequestDto);
        return ResponseEntity.ok(userMapper.mapToUserResponseDto(user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting an user by id")
    public ResponseEntity<UserResponseDto> delete(@PathVariable(name = "id") Long id) {
        User user = userService.delete(id);
        return ResponseEntity.ok(userMapper.mapToUserResponseDto(user));
    }
}

package me.project.travle.controller;

import me.project.travle.dto.user.UserRequestDto;
import me.project.travle.dto.user.UserResponseDto;
import me.project.travle.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {

    private final UsersService usersService;

    UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody UserRequestDto userRequestDto) {
        return usersService.createUser(userRequestDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto findUserById(@PathVariable String id) {
        return usersService.findUserById(id);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequestDto userRequestDto) {
        return usersService.login(userRequestDto);
    }

}

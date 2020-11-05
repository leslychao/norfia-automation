package com.vkim.skyeng.controller;

import com.vkim.skyeng.dto.UserDto;
import com.vkim.skyeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto saveReport(UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping("admin")
    @ResponseBody
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

}

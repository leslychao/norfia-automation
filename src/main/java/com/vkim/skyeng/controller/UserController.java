package com.vkim.skyeng.controller;

import com.vkim.skyeng.dto.UserDto;
import com.vkim.skyeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
  public UserDto saveUser(UserDto userDto) {
    return userService.saveUser(userDto);
  }

}

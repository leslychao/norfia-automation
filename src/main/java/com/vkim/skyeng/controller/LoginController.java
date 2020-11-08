package com.vkim.skyeng.controller;

import com.vkim.skyeng.dto.UserDto;
import com.vkim.skyeng.exceptions.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

  @GetMapping
  public String loginPage() {
    return "login";
  }

  @GetMapping("error")
  public String error() {
    throw new AuthenticationException();
  }

  @GetMapping("register")
  public String registerPage(@ModelAttribute UserDto userDto) {
    return "register";
  }

}

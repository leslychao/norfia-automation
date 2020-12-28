package com.vkim.norfia.controller;

import static com.vkim.norfia.util.Constants.DEFAULT_USER_ROLES;

import com.vkim.norfia.dto.UserDetailsDto;
import com.vkim.norfia.dto.UserDto;
import com.vkim.norfia.exceptions.AuthenticationException;
import com.vkim.norfia.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

  private UserService userService;

  @Autowired
  public LoginController(UserService userService) {
    this.userService = userService;
  }

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
    userDto.setUserDetails(new UserDetailsDto());
    return "register";
  }

  @PostMapping("register")
  public String register(@RequestParam String confirmPassword, UserDto userDto, Model model) {
    if (!StringUtils.equals(confirmPassword, userDto.getUserDetails().getPassword())) {
      model.addAttribute("error", "Passwords not match");
      return "register";
    }
    userDto.getUserDetails().setUserRoles(DEFAULT_USER_ROLES);
    userDto.getUserDetails().setAccountNonExpired(true);
    userDto.getUserDetails().setAccountNonLocked(true);
    userDto.getUserDetails().setCredentialsNonExpired(true);
    userDto.getUserDetails().setEnabled(true);
    userService.saveUser(userDto);
    return "login";
  }

}

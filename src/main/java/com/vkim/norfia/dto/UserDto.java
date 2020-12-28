package com.vkim.norfia.dto;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class UserDto extends BaseDto {

  private String userName;
  private String firstName;
  private String lastName;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate birthDate;
  private UserDetailsDto userDetails;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public UserDetailsDto getUserDetails() {
    return userDetails;
  }

  public void setUserDetails(UserDetailsDto userDetails) {
    this.userDetails = userDetails;
  }
}

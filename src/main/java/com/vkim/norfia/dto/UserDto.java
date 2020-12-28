package com.vkim.norfia.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class UserDto extends BaseDto {

  private String userName;
  private String firstName;
  private String lastName;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate birthDate;
  private UserDetailsDto userDetails;

}

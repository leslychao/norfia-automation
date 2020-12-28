package com.vkim.norfia.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "sequence_generator", sequenceName = "users_seq", allocationSize = 1)
public class UserEntity extends LongIdEntity {

  private String userName;
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @PrimaryKeyJoinColumn
  private UserDetailsEntity userDetails;

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

  public UserDetailsEntity getUserDetails() {
    return userDetails;
  }

  public void setUserDetails(UserDetailsEntity userDetails) {
    this.userDetails = userDetails;
  }
}

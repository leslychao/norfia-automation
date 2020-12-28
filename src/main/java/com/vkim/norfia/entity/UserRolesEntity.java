package com.vkim.norfia.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
public class UserRolesEntity extends LongIdEntity {

  private String name;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserDetailsEntity userDetails;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserDetailsEntity getUserDetails() {
    return userDetails;
  }

  public void setUserDetails(UserDetailsEntity userDetails) {
    this.userDetails = userDetails;
  }
}

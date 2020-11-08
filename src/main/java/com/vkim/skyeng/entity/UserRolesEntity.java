package com.vkim.skyeng.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
@SequenceGenerator(name = "sequence_generator", sequenceName = "user_roles_seq", allocationSize = 1)
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

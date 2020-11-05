package com.vkim.skyeng.entity;

import javax.persistence.*;
import java.time.LocalDate;

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

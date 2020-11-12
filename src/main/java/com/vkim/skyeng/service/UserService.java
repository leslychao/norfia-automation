package com.vkim.skyeng.service;

import com.vkim.skyeng.dto.UserDto;
import com.vkim.skyeng.entity.UserDetailsEntity;
import com.vkim.skyeng.entity.UserEntity;
import com.vkim.skyeng.entity.UserRolesEntity;
import com.vkim.skyeng.mapper.BeanMapper;
import com.vkim.skyeng.mapper.UserMapper;
import com.vkim.skyeng.repository.UserRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class UserService extends AbstractCrudService<UserDto, UserEntity> {

  private UserRepository userRepository;
  private UserMapper userMapper;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, UserMapper userMapper,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
  }

  public UserDto saveUser(UserDto userDto) {
    return save(userDto);
  }

  @Override
  protected BeanMapper<UserDto, UserEntity> getMapper() {
    return userMapper;
  }

  @Override
  protected JpaRepository<UserEntity, Long> getRepository() {
    return userRepository;
  }

  @Override
  protected UserEntity entityPreSaveAction(UserEntity entity) {
    UserDetailsEntity userDetails = entity.getUserDetails();
    userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
    userDetails.setUser(entity);
    Set<UserRolesEntity> userRoles = userDetails.getUserRoles().stream().map(userRolesEntity -> {
      userRolesEntity.setUserDetails(userDetails);
      return userRolesEntity;
    }).collect(Collectors.toSet());
    userDetails.setUserRoles(userRoles);
    return super.entityPreSaveAction(entity);
  }
}

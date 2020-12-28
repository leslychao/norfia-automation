package com.vkim.norfia.service;

import com.vkim.norfia.dto.UserDto;
import com.vkim.norfia.entity.UserDetailsEntity;
import com.vkim.norfia.entity.UserEntity;
import com.vkim.norfia.entity.UserRolesEntity;
import com.vkim.norfia.mapper.BeanMapper;
import com.vkim.norfia.mapper.UserMapper;
import com.vkim.norfia.repository.UserRepository;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
@RequiredArgsConstructor
public class UserService extends AbstractCrudService<UserDto, UserEntity> {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

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
  protected UserEntity updateEntityWithDto(UserEntity entity, UserDto dto) {
    return entity;
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

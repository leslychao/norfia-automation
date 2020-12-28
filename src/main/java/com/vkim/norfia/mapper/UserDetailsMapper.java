package com.vkim.norfia.mapper;

import com.vkim.norfia.dto.UserDetailsDto;
import com.vkim.norfia.entity.UserDetailsEntity;
import com.vkim.norfia.entity.UserRolesEntity;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper extends BeanMapper<UserDetailsDto, UserDetailsEntity> {

  default Set<String> fromEntityUserRoles(Set<UserRolesEntity> userRoles) {
    return userRoles.stream()
        .map(userRolesEntity -> userRolesEntity.getName())
        .collect(Collectors.toSet());
  }

  default Set<UserRolesEntity> fromStringUserRoles(Set<String> userRoles) {
    return userRoles.stream().map(s -> {
      UserRolesEntity userRolesEntity = new UserRolesEntity();
      userRolesEntity.setName(s);
      return userRolesEntity;
    }).collect(Collectors.toSet());
  }
}

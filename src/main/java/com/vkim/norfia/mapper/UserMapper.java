package com.vkim.norfia.mapper;

import com.vkim.norfia.dto.UserDto;
import com.vkim.norfia.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserDetailsMapper.class})
public interface UserMapper extends BeanMapper<UserDto, UserEntity> {

}

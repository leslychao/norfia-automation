package com.vkim.skyeng.mapper;

import com.vkim.skyeng.dto.UserDto;
import com.vkim.skyeng.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserDetailsMapper.class})
public interface UserMapper extends BeanMapper<UserDto, UserEntity> {

}

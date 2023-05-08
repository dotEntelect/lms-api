package com.dot.lmsapi.features.manageUsers.services;

import com.dot.lmsapi.features.manageUsers.models.User;
import com.dot.lmsapi.features.manageUsers.models.UserDto;
import com.dot.lmsapi.features.manageUsers.models.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto toDto(User user);
    User fromDto(UserDto userDto);

    UserResponseDto toResponseDto(User user);
}

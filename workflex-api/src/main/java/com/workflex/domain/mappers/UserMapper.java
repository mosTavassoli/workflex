package com.workflex.domain.mappers;

import com.workflex.domain.dtos.UserDto;
import com.workflex.domain.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface  UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}

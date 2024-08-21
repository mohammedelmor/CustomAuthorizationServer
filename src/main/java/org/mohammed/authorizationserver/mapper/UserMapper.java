package org.mohammed.authorizationserver.mapper;

import org.mapstruct.*;
import org.mohammed.authorizationserver.dto.UserGetDto;
import org.mohammed.authorizationserver.dto.UserMinimalGetDto;
import org.mohammed.authorizationserver.dto.UserPostDto;
import org.mohammed.authorizationserver.model.User;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserPermissionMapper.class, GroupMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    @Mapping(target = "username", source = "user")
    @Mapping(target = "password", source = "credentials")
    User toEntity(UserPostDto dto);

    UserGetDto toDto(User user);

    UserMinimalGetDto toMinimalDto(User user);

    List<UserGetDto> toDto(List<User> users);

    List<UserMinimalGetDto> toMinimalDto(List<User> users);
}
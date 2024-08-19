package org.mohammed.authorizationserver.mapper;

import org.mapstruct.*;
import org.mohammed.authorizationserver.dto.UserPermissionGetDto;
import org.mohammed.authorizationserver.dto.UserPermissionPostDto;
import org.mohammed.authorizationserver.model.UserPermission;
import org.mohammed.authorizationserver.service.GroupService;
import org.mohammed.authorizationserver.service.PermissionService;
import org.mohammed.authorizationserver.service.UserPermissionService;
import org.mohammed.authorizationserver.service.UserService;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserPermissionService.class, PermissionService.class, GroupService.class},
        injectionStrategy = InjectionStrategy.SETTER)
public interface UserPermissionMapper {

    @Mapping(target = "user", source = "userId", qualifiedByName = "findUserById")
    @Mapping(target = "permission", source = "permissionId", qualifiedByName = "findPermissionById")
    @Mapping(target = "group", source = "groupId", qualifiedByName = "findGroupById")
    UserPermission toEntity(UserPermissionPostDto dto);

    @Mapping(target = "user.id", source = "user.id")
    @Mapping(target = "user.username", source = "user.username")
    @Mapping(target = "permission.id", source = "permission.id")
    @Mapping(target = "permission.name", source = "permission.name")
    @Mapping(target = "group.id", source = "group.id")
    @Mapping(target = "group.name", source = "group.name")
    UserPermissionGetDto toDto(UserPermission userPermission);

    List<UserPermissionGetDto> toDto(List<UserPermission> userPermissions);
}
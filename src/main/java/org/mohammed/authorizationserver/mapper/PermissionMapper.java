package org.mohammed.authorizationserver.mapper;

import org.mapstruct.*;
import org.mohammed.authorizationserver.dto.PermissionGetDto;
import org.mohammed.authorizationserver.dto.PermissionPostDto;
import org.mohammed.authorizationserver.model.Permission;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissionMapper {
    Permission toEntity(PermissionPostDto dto);

    PermissionGetDto toDto(Permission permission);
    List<PermissionGetDto> toDto(List<Permission> permission);
}
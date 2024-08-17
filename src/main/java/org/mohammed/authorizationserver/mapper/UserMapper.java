package org.mohammed.authorizationserver.mapper;

import org.mapstruct.*;
import org.mohammed.authorizationserver.dto.UserMinimalGetDto;
import org.mohammed.authorizationserver.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserMinimalGetDto userMinimalGetDto);

    UserMinimalGetDto toDto(User user);

}
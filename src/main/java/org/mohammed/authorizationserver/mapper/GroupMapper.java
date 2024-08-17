package org.mohammed.authorizationserver.mapper;

import org.mapstruct.*;
import org.mohammed.authorizationserver.dto.GroupGetDto;
import org.mohammed.authorizationserver.dto.GroupPostDto;
import org.mohammed.authorizationserver.model.Group;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper {
    Group toEntity(GroupPostDto dto);

    GroupGetDto toDto(Group group);
    List<GroupGetDto> toDto(List<Group> groups);

}
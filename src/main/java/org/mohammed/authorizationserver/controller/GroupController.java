package org.mohammed.authorizationserver.controller;

import org.mohammed.authorizationserver.dto.GroupGetDto;
import org.mohammed.authorizationserver.dto.GroupPostDto;
import org.mohammed.authorizationserver.mapper.GroupMapper;
import org.mohammed.authorizationserver.service.GroupService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;

    public GroupController(GroupService groupService, GroupMapper groupMapper) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
    }

    @GetMapping
    public Page<GroupGetDto> findAll(int page, int size) {
        return groupService.findAll(page, size).map(groupMapper::toDto);
    }

    @GetMapping("/{id}")
    public GroupGetDto findById(@PathVariable Long id) {
        return groupMapper.toDto(groupService.findById(id));
    }

    @PostMapping
    public GroupGetDto create(GroupPostDto dto) {
        return groupMapper.toDto(groupService.create(dto));
    }

    @PutMapping("/{id}")
    public GroupGetDto update(GroupPostDto dto, @PathVariable Long id) {
        return groupMapper.toDto(groupService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }



}

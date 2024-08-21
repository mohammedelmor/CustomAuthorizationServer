package org.mohammed.authorizationserver.controller;

import org.mohammed.authorizationserver.dto.GroupGetDto;
import org.mohammed.authorizationserver.dto.GroupPostDto;
import org.mohammed.authorizationserver.mapper.GroupMapper;
import org.mohammed.authorizationserver.service.GroupService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<GroupGetDto>> findAll(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(groupService.findAll(page, size).map(groupMapper::toDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupGetDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(groupMapper.toDto(groupService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<GroupGetDto> create(@RequestBody GroupPostDto dto) {
        return ResponseEntity.ok(groupMapper.toDto(groupService.create(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupGetDto> update(@RequestBody GroupPostDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(groupMapper.toDto(groupService.update(dto, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity.ok().build();
    }



}

package org.mohammed.authorizationserver.controller;

import org.mohammed.authorizationserver.dto.UserPermissionGetDto;
import org.mohammed.authorizationserver.dto.UserPermissionPostDto;
import org.mohammed.authorizationserver.mapper.UserPermissionMapper;
import org.mohammed.authorizationserver.service.UserPermissionService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-permissions")
public class UserPermissionController {

    private final UserPermissionService userPermissionService;
    private final UserPermissionMapper userPermissionMapper;

    public UserPermissionController(UserPermissionService userPermissionService, UserPermissionMapper userPermissionMapper) {
        this.userPermissionService = userPermissionService;
        this.userPermissionMapper = userPermissionMapper;
    }

    @GetMapping
    public Page<UserPermissionGetDto> findAll(int page, int size) {
        return userPermissionService.findAll(page, size).map(userPermissionMapper::toDto);
    }

    @GetMapping("/{id}")
    public UserPermissionGetDto findById(@PathVariable Long id) {
        return userPermissionMapper.toDto(userPermissionService.findById(id));
    }

    @PostMapping
    public UserPermissionGetDto assignUserPermissionToUser(UserPermissionPostDto dto) {
        return userPermissionMapper.toDto(userPermissionService.assignUserPermissionToUser(dto));
    }

    @DeleteMapping("/{id}")
    public void removeUserPermissionFromUser(@PathVariable Long id) {
        userPermissionService.removeUserPermissionFromUser(id);
    }

}

package org.mohammed.authorizationserver.controller;

import org.mohammed.authorizationserver.dto.UserPermissionGetDto;
import org.mohammed.authorizationserver.dto.UserPermissionPostDto;
import org.mohammed.authorizationserver.mapper.UserPermissionMapper;
import org.mohammed.authorizationserver.service.UserPermissionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<UserPermissionGetDto>> findAll(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(userPermissionService.findAll(page, size).map(userPermissionMapper::toDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPermissionGetDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userPermissionMapper.toDto(userPermissionService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserPermissionGetDto> assignUserPermissionToUser(@RequestBody UserPermissionPostDto dto) {
        return ResponseEntity.ok(userPermissionMapper.toDto(userPermissionService.assignUserPermissionToUser(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUserPermissionFromUser(@PathVariable Long id) {
        userPermissionService.removeUserPermissionFromUser(id);
        return ResponseEntity.ok().build();
    }

}

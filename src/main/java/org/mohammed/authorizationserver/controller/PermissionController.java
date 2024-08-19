package org.mohammed.authorizationserver.controller;

import org.mohammed.authorizationserver.dto.PermissionGetDto;
import org.mohammed.authorizationserver.dto.PermissionPostDto;
import org.mohammed.authorizationserver.mapper.PermissionMapper;
import org.mohammed.authorizationserver.service.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;
    private final PermissionMapper permissionMapper;

    public PermissionController(PermissionService permissionService, PermissionMapper permissionMapper) {
        this.permissionService = permissionService;
        this.permissionMapper = permissionMapper;
    }

    @GetMapping
    public Page<PermissionGetDto> findAll(int page, int size) {
        return permissionService.findAll(page, size).map(permissionMapper::toDto);
    }

    @GetMapping("/{id}")
    public PermissionGetDto findById(@PathVariable Long id) {
        return permissionMapper.toDto(permissionService.findById(id));
    }

    @PostMapping
    public PermissionGetDto create(PermissionPostDto dto) {
        return permissionMapper.toDto(permissionService.create(dto));
    }

    @PutMapping("/{id}")
    public PermissionGetDto update(PermissionPostDto dto, @PathVariable Long id) {
        return permissionMapper.toDto(permissionService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        permissionService.delete(id);
    }

}

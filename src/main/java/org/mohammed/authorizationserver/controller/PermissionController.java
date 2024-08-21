package org.mohammed.authorizationserver.controller;

import org.mohammed.authorizationserver.dto.PermissionGetDto;
import org.mohammed.authorizationserver.dto.PermissionPostDto;
import org.mohammed.authorizationserver.mapper.PermissionMapper;
import org.mohammed.authorizationserver.service.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<PermissionGetDto>> findAll(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(permissionService.findAll(page, size).map(permissionMapper::toDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionGetDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(permissionMapper.toDto(permissionService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PermissionGetDto> create(@RequestBody PermissionPostDto dto) {
        return ResponseEntity.ok(permissionMapper.toDto(permissionService.create(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionGetDto> update(@RequestBody PermissionPostDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(permissionMapper.toDto(permissionService.update(dto, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        permissionService.delete(id);
        return ResponseEntity.ok().build();
    }

}

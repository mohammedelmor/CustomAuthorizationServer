package org.mohammed.authorizationserver.service;

import jakarta.transaction.Transactional;
import org.mapstruct.Named;
import org.mohammed.authorizationserver.dto.PermissionPostDto;
import org.mohammed.authorizationserver.exception.PermissionAlreadyExistsException;
import org.mohammed.authorizationserver.exception.PermissionNotFoundException;
import org.mohammed.authorizationserver.mapper.PermissionMapper;
import org.mohammed.authorizationserver.model.Permission;
import org.mohammed.authorizationserver.repository.PermissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public PermissionService(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    @Named("findById")
    public Permission findById(Long id) {
        var permission = permissionRepository.findById(id);
        if (permission.isEmpty()) {
            throw new PermissionNotFoundException("Permission with id: " + id + " does not exist");
        }
        return permission.get();
    }

    public Permission findByName(String name) {
        var permission = permissionRepository.findByName(name);
        if (permission.isEmpty()) {
            throw new PermissionNotFoundException("Permission with name: " + name + " does not exist");
        }
        return permission.get();
    }

    public Page<Permission> findAll(int page, int size) {
        return permissionRepository.findAll(PageRequest.of(page, size));
    }

    public Permission create(PermissionPostDto dto) {
        if (permissionRepository.findByName(dto.name()).isPresent()) {
            throw new PermissionAlreadyExistsException("Permission " + dto.name() + " already exists");
        }
        return permissionRepository.save(permissionMapper.toEntity(dto));
    }

    public Permission update(PermissionPostDto dto, Long id) {
        Optional<Permission> permission = permissionRepository.findById(id);
        if (permission.isEmpty()) {
            throw new PermissionNotFoundException("Permission with id: " + id + " does not exist");
        }
        var updatedEntity = permission.map(p -> {
           p.setName(dto.name());
           return p;
        }).get();
        return permissionRepository.save(updatedEntity);
    }

    public void delete(Long id) {
        if (permissionRepository.findById(id).isEmpty()) {
            throw new PermissionNotFoundException("Permission with id: " + id + " does not exist");
        }
        permissionRepository.deleteById(id);
    }



}

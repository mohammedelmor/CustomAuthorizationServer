package org.mohammed.authorizationserver.service;

import jakarta.transaction.Transactional;
import org.mapstruct.Named;
import org.mohammed.authorizationserver.dto.UserPermissionPostDto;
import org.mohammed.authorizationserver.exception.GroupNotFoundException;
import org.mohammed.authorizationserver.exception.PermissionNotFoundException;
import org.mohammed.authorizationserver.exception.UserNotFoundException;
import org.mohammed.authorizationserver.exception.UserPermissionNotFoundException;
import org.mohammed.authorizationserver.mapper.UserPermissionMapper;
import org.mohammed.authorizationserver.model.Group;
import org.mohammed.authorizationserver.model.Permission;
import org.mohammed.authorizationserver.model.User;
import org.mohammed.authorizationserver.model.UserPermission;
import org.mohammed.authorizationserver.repository.GroupRepository;
import org.mohammed.authorizationserver.repository.PermissionRepository;
import org.mohammed.authorizationserver.repository.UserPermissionRepository;
import org.mohammed.authorizationserver.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserPermissionService {

    private final UserPermissionRepository userPermissionRepository;
    private final UserPermissionMapper userPermissionMapper;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final GroupRepository groupRepository;

    public UserPermissionService(UserPermissionRepository userPermissionRepository,
                                 @Lazy UserPermissionMapper userPermissionMapper,
                                 UserRepository userRepository,
                                 PermissionRepository permissionRepository,
                                 GroupRepository groupRepository) {
        this.userPermissionRepository = userPermissionRepository;
        this.userPermissionMapper = userPermissionMapper;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.groupRepository = groupRepository;
    }

    public UserPermission findById(Long id) {
        var userPermission = userPermissionRepository.findById(id);
        if (userPermission.isEmpty()) {
            throw new UserPermissionNotFoundException("UserPermission with id: " + id + " does not exist");
        }
        return userPermission.get();
    }

    public Page<UserPermission> findAll(int page, int size) {
        return userPermissionRepository.findAll(PageRequest.of(page, size));
    }

    public UserPermission assignUserPermissionToUser(UserPermissionPostDto dto) {
        return userPermissionRepository.save(userPermissionMapper.toEntity(dto));
    }

    public void removeUserPermissionFromUser(Long id) {
        if (userPermissionRepository.findById(id).isEmpty()) {
            throw new UserPermissionNotFoundException("UserPermission with id: " + id + " does not exist");
        }
        userPermissionRepository.deleteById(id);
    }

    @Named("findUserById")
    public User findUserById(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + " does not exist");
        }
        return user.get();
    }

    @Named("findPermissionById")
    public Permission findPermissionById(Long id) {
        var permission = permissionRepository.findById(id);
        if (permission.isEmpty()) {
            throw new PermissionNotFoundException("Permission with id: " + id + " does not exist");
        }
        return permission.get();
    }

    @Named("findGroupById")
    public Group findGroupById(Long id) {
        var group = groupRepository.findById(id);
        if (group.isEmpty()) {
            throw new GroupNotFoundException("Group with id: " + id + " does not exist");
        }
        return group.get();
    }

}

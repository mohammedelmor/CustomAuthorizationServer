package org.mohammed.authorizationserver.config;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.authorizationserver.model.Group;
import org.mohammed.authorizationserver.model.Permission;
import org.mohammed.authorizationserver.model.User;
import org.mohammed.authorizationserver.repository.GroupRepository;
import org.mohammed.authorizationserver.repository.PermissionRepository;
import org.mohammed.authorizationserver.repository.UserPermissionRepository;
import org.mohammed.authorizationserver.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@Transactional
@Slf4j
public class DataSeedConfig {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final GroupRepository groupRepository;
    private final UserPermissionRepository userPermissionRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeedConfig(UserRepository userRepository, PermissionRepository permissionRepository, GroupRepository groupRepository, UserPermissionRepository userPermissionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.groupRepository = groupRepository;
        this.userPermissionRepository = userPermissionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void seedData() {
        log.info("Deleting all data and seeding fresh data");
        userPermissionRepository.deleteAll();
        userPermissionRepository.flush();
        userRepository.deleteAll();
        userRepository.flush();
        permissionRepository.deleteAll();
        permissionRepository.flush();
        groupRepository.deleteAll();
        groupRepository.flush();
        seedGroups();
        seedPermissions();
        seedUsers();
    }


    public void seedGroups() {
        Group group1 = new Group();
        group1.setName("HR");

        Group group2 = new Group();
        group2.setName("Finance");

        Group group3 = new Group();
        group3.setName("Admin");

        List<Group> groups = List.of(group1, group2, group3);
        groupRepository.saveAll(groups);
    }


    public void seedPermissions() {
        Permission permission1 = new Permission();
        permission1.setName("READ");

        Permission permission2 = new Permission();
        permission2.setName("WRITE");

        Permission permission3 = new Permission();
        permission3.setName("DELETE");

        List<Permission> permissions = List.of(permission1, permission2, permission3);
        permissionRepository.saveAll(permissions);
    }


    public void seedUsers() {
        List<Permission> permissions = permissionRepository.findAll();
        List<Group> groups = groupRepository.findAll();

        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword(passwordEncoder.encode("password"));
        userRepository.save(user1);

        user1.addUserPermission(permissions.get(0), groups.get(0));
        user1.addUserPermission(permissions.get(1), groups.get(1));
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword(passwordEncoder.encode("password"));
        userRepository.save(user2);

        user2.addUserPermission(permissions.get(0), groups.get(0));
        user2.addUserPermission(permissions.get(1), groups.get(1));
        userRepository.save(user2);
    }

}

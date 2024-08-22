package org.mohammed.authorizationserver.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mohammed.authorizationserver.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
@ActiveProfiles("test")
public class PermissionRepositoryTests {

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void PermissionRepository_SavePermission_ReturnsSavedPermission() {
        // Arrange
        var permission = new Permission();
        permission.setName("TEST");
        // Act
        var savedPermission = permissionRepository.save(permission);
        // Assert
        Assertions.assertThat(savedPermission).isNotNull();
        Assertions.assertThat(savedPermission.getId()).isGreaterThan(0);
    }

    @Test
    public void PermissionRepository_SaveAll_ReturnsMoreThanOnePermission() {
        // Arrange
        var permission1 = new Permission();
        permission1.setName("TEST1");
        var permission2 = new Permission();
        permission2.setName("TEST2");
        permissionRepository.saveAll(List.of(permission1, permission2));
        // Act
        var savedPermissions = permissionRepository.findAll();
        // Assert
        Assertions.assertThat(savedPermissions).isNotNull();
        Assertions.assertThat(savedPermissions.size()).isEqualTo(2);
    }

    @Test
    public void PermissionRepository_FindById_ReturnsPermission() {
        // Arrange
        var permission = new Permission();
        permission.setName("TEST1");
        permissionRepository.save(permission);
        // Act
        var savedPermission = permissionRepository.findById(permission.getId()).get();
        // Assert
        Assertions.assertThat(savedPermission).isNotNull();
        Assertions.assertThat(savedPermission.getName()).isEqualTo("TEST1");
    }

    @Test
    public void PermissionRepository_FindByName_ReturnsPermission() {
        // Arrange
        var permission = new Permission();
        permission.setName("TEST1");
        permissionRepository.save(permission);
        // Act
        var savedPermission = permissionRepository.findByName("TEST1");
        // Assert
        Assertions.assertThat(savedPermission).isNotNull();
        Assertions.assertThat(savedPermission.get().getName()).isEqualTo("TEST1");
    }

    @Test
    public void PermissionRepository_DeletePermission_ReturnsNoPermission() {
        // Arrange
        var permission = new Permission();
        permission.setName("TEST1");
        permissionRepository.save(permission);
        // Act
        permissionRepository.delete(permission);
        var savedPermission = permissionRepository.findById(permission.getId());
        // Assert
        Assertions.assertThat(savedPermission).isEmpty();
    }

    @Test
    public void PermissionRepository_DeleteAll_ReturnsNoPermission() {
        // Arrange
        var permission1 = new Permission();
        permission1.setName("TEST1");
        var permission2 = new Permission();
        permission2.setName("TEST2");
        permissionRepository.saveAll(List.of(permission1, permission2));
        // Act
        permissionRepository.deleteAll();
        var savedPermissions = permissionRepository.findAll();
        // Assert
        Assertions.assertThat(savedPermissions).isEmpty();
    }

    @Test
    public void PermissionRepository_Update_ReturnsUpdatedPermission() {
        // Arrange
        var permission = new Permission();
        permission.setName("TEST1");
        var savedPermission = permissionRepository.save(permission);
        // Act
        savedPermission.setName("TEST1_UPDATED");
        permissionRepository.save(savedPermission);
        var updatedPermission = permissionRepository.findById(savedPermission.getId()).get();
        // Assert
        Assertions.assertThat(updatedPermission.getName()).isEqualTo("TEST1_UPDATED");
    }



}

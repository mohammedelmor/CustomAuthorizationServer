package org.mohammed.authorizationserver.repository;

import org.mohammed.authorizationserver.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
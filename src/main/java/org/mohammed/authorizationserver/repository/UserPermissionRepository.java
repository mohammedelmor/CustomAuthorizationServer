package org.mohammed.authorizationserver.repository;

import org.mohammed.authorizationserver.model.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {
}
package org.mohammed.authorizationserver.repository;

import org.mohammed.authorizationserver.model.UserPermission;
import org.mohammed.authorizationserver.model.UserPermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {

    @Query("SELECT u FROM UserPermission u LEFT JOIN FETCH u.permission p LEFT JOIN FETCH u.group g WHERE u.id = :userPermissionId")
    Optional<UserPermission> findByUserPermissionId(UserPermissionId userPermissionId);

}
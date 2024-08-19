package org.mohammed.authorizationserver.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link org.mohammed.authorizationserver.model.UserPermission}
 */
public record UserPermissionPostDto(
        @NotNull(message = "User Permission must have an associated user") Long userId,
        @NotNull(message = "User Permission must have an associated permission") Long permissionId,
        @NotNull(message = "User Permission must have an associated group") Long groupId) implements Serializable {
}
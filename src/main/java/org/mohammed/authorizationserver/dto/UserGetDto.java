package org.mohammed.authorizationserver.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link org.mohammed.authorizationserver.model.User}
 */
public record UserGetDto(Long id, @NotNull String username,
                         Set<UserPermissionGetDto> userPermissions) implements Serializable {
}
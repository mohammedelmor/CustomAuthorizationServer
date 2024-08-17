package org.mohammed.authorizationserver.dto;

import java.io.Serializable;

/**
 * DTO for {@link org.mohammed.authorizationserver.model.UserPermission}
 */
public record UserPermissionGetDto(Long id, UserMinimalGetDto user, PermissionGetDto permission,
                                   GroupGetDto group) implements Serializable {
}
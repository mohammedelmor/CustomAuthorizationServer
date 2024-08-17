package org.mohammed.authorizationserver.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link org.mohammed.authorizationserver.model.Permission}
 */
public record PermissionPostDto(@NotNull(message = "Permission must have a name") String name) implements Serializable {
}
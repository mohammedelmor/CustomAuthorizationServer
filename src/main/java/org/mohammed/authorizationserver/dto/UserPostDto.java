package org.mohammed.authorizationserver.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link org.mohammed.authorizationserver.model.User}
 */
public record UserPostDto(
        @NotNull(message = "username required") @Size(message = "username length must be at least 3 characters", min = 3) String user,
        @NotNull(message = "password required") String credentials) implements Serializable {
}
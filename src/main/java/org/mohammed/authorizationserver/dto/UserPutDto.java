package org.mohammed.authorizationserver.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link org.mohammed.authorizationserver.model.User}
 */
public record UserPutDto(@NotNull(message = "password required") String credentials) implements Serializable {
}
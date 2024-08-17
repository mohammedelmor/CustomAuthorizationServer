package org.mohammed.authorizationserver.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link org.mohammed.authorizationserver.model.User}
 */
public record UserMinimalGetDto(Long id, @NotNull String username) implements Serializable {
}
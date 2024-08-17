package org.mohammed.authorizationserver.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link org.mohammed.authorizationserver.model.Group}
 */
public record GroupGetDto(Long id, @NotNull String name) implements Serializable {
}
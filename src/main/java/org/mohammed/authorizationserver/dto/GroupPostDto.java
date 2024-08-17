package org.mohammed.authorizationserver.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link org.mohammed.authorizationserver.model.Group}
 */
public record GroupPostDto(@NotNull(message = "Group must have a name") String name) implements Serializable {
}
package org.mohammed.authorizationserver.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class UserPermissionId implements Serializable {
    private Long userId;
    private Long permissionId;
    private Long groupId;
}

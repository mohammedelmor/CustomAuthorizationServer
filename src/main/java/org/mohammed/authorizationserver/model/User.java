package org.mohammed.authorizationserver.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String username;

    @Column(nullable = false)
    @NotNull
    private String password;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserPermission> userPermissions = new HashSet<>();


    public void addUserPermission(Permission permission, Group group) {
        UserPermission userPermission = new UserPermission();
        userPermission.setUser(this);
        userPermission.setPermission(permission);
        userPermission.setGroup(group);
        userPermissions.add(userPermission);
    }
}

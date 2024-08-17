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
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_id_seq")
    @SequenceGenerator(name = "permission_id_seq", sequenceName = "permission_id_seq")
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "permission",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private Set<UserPermission> userPermissions = new HashSet<>();

}

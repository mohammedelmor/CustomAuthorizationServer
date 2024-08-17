package org.mohammed.authorizationserver.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "user_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_group_id_seq")
    @SequenceGenerator(name = "user_group_id_seq", sequenceName = "user_group_id_seq")
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "group"
    )
    private Set<UserPermission> userPermissions = new HashSet<>();
}

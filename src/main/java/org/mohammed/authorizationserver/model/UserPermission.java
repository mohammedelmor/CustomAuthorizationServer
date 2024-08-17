package org.mohammed.authorizationserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"user_id", "permission_id", "group_id"})
})
public class UserPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_permission_id_seq")
    @SequenceGenerator(name = "user_permission_id_seq", sequenceName = "user_permission_id_seq")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "group_id")
    private Group group;

}

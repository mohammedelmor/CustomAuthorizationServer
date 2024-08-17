package org.mohammed.authorizationserver.repository;

import org.mohammed.authorizationserver.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
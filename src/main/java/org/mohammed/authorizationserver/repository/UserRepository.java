package org.mohammed.authorizationserver.repository;

import org.mohammed.authorizationserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userPermissions up LEFT JOIN FETCH up.permission LEFT JOIN FETCH up.group WHERE u.username = :username")
    Optional<User> findByUsername(String username);
}
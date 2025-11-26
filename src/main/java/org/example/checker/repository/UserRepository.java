package org.example.checker.repository;

import org.example.checker.model.User;
import org.example.checker.model.invariants.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByRole(Role role);
}

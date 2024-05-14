package com.example.StudyWithMe.repositories.user.auth;

import com.example.StudyWithMe.models.user.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT r FROM Role r WHERE name = :roleName")
    Optional<Role> findByRoleName(String roleName);
}

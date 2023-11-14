package com.ScootersApp.repository;

import com.ScootersApp.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    boolean existsByType(String tipo);
}

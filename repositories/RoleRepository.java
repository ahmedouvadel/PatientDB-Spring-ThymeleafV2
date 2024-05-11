package com.exmple.patientsmvc.repositories;

import com.exmple.patientsmvc.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}

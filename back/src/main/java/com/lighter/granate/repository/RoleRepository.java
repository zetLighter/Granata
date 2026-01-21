package com.lighter.granate.repository;

import com.lighter.granate.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByTitle(String title);
    boolean existsByTitle(String title);
}

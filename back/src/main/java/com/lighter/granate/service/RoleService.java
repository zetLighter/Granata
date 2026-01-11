package com.lighter.granate.service;

import com.lighter.granate.entity.Role;
import com.lighter.granate.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getUserRole() {
        return roleRepository.findByTitle("ROLE_USER");
    }

    public Role getAdminRole() {
        return roleRepository.findByTitle("ROLE_ADMIN");
    }
}

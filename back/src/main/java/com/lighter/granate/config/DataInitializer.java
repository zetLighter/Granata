package com.lighter.granate.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lighter.granate.entity.Role;
import com.lighter.granate.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void run(String... args) {
        if(!roleRepository.existsByTitle("ROLE_USER")) {
            roleRepository.save(Role.builder().title("ROLE_USER").build());
        }
        if(!roleRepository.existsByTitle("ROLE_ADMIN")) {
            roleRepository.save(Role.builder().title("ROLE_ADMIN").build());
        }

    }
}

package com.auth.auth.service;

import com.auth.auth.entity.Role;
import com.auth.auth.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleSeederService {

    private final RoleRepository roleRepository;

    public RoleSeederService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void seedRoles() {

        List<String> roles = List.of("ROLE_ADMIN", "ROLE_FUND", "ROLE_DISTRIBUTOR", "ROLE_USER");

        for (String roleName : roles) {
            roleRepository.findByName(roleName)
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName(roleName);
                        return roleRepository.save(role);
                    });
        }
    }
}
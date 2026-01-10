package com.auth.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.auth.auth.entity.Role;
import com.auth.auth.repository.RoleRepository;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	public CommandLineRunner initRoles(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByName("ROLE_USER").isEmpty()) {
				roleRepository.save(new Role("ROLE_USER"));
			}
			if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
				roleRepository.save(new Role("ROLE_ADMIN"));
			}
			if (roleRepository.findByName("ROLE_DISTRIBUTOR").isEmpty()) {
				roleRepository.save(new Role("ROLE_DISTRIBUTOR"));
			}
			if (roleRepository.findByName("ROLE_FUND").isEmpty()) {
				roleRepository.save(new Role("ROLE_FUND"));
			}
		};
	}

}

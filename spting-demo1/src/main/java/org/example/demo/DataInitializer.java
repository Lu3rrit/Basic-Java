package org.example.demo;

import lombok.AllArgsConstructor;
import org.example.demo.domain.User;
import org.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            userRepository.save(new User(null, "admin", passwordEncoder.encode("admin"), "ROLE_ADMIN", null));
            userRepository.save(new User(null, "teacher1", passwordEncoder.encode("teacher1"), "ROLE_TEACHER", "CLASS_A"));
            userRepository.save(new User(null, "student1", passwordEncoder.encode("student1"), "ROLE_STUDENT", "CLASS_A"));
        }
    }
}

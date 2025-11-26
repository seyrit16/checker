package org.example.checker.configuration.init;

import org.example.checker.dto.adjacent.SignUpRequest;
import org.example.checker.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseInitializationConfig {
    private final UserService userService;

    public DataBaseInitializationConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public CommandLineRunner initDataBase() {
        return args -> {
            if (!userService.existsByRole("ADMIN"))
                userService.save(new SignUpRequest("ADMIN", "ADMIN@mail.ru","ADMIN", "ADMIN"), "ADMIN");
        };
    }
}

package org.example.checker.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String phone;
    // List<EventsDto> favoriteEvents
}

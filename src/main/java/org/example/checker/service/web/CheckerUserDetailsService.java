package org.example.checker.service.web;

import org.example.checker.configuration.security.components.CheckerUserDetails;
import org.example.checker.dto.UserDto;
import org.example.checker.model.User;
import org.example.checker.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckerUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CheckerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUser(userRepository.findByEmail(username)).map(CheckerUserDetails::new).orElseThrow(() ->
            new UsernameNotFoundException("User not found with email: " + username));
    }

    private Optional<UserDto> getUser(Optional<User> user) {
        if (user.isEmpty()) {
            return Optional.empty();
        }

        User usr = user.get();
        UserDto userDto = new UserDto();

        userDto.setId(usr.getId());
        userDto.setName(usr.getName());
        userDto.setEmail(usr.getEmail());
        userDto.setPassword(usr.getPassword());
        userDto.setRole(usr.getRole().toString());
        userDto.setPhone(usr.getPhone());

        return Optional.of(userDto);
    }
}

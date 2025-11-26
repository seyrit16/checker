package org.example.checker.service.web;

import org.example.checker.configuration.security.components.CheckerUserDetails;
import org.example.checker.dto.adjacent.JwtAuthenticationResponse;
import org.example.checker.dto.adjacent.SignInRequest;
import org.example.checker.dto.adjacent.SignUpRequest;
import org.example.checker.dto.adjacent.UserData;
import org.example.checker.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final CheckerUserDetailsService checkerUserDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserService userService,
                                 JwtService jwtService,
                                 CheckerUserDetailsService checkerUserDetailsService,
                                 AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.checkerUserDetailsService = checkerUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    private JwtAuthenticationResponse getJwtAuthenticationResponse(String login) {
        UserDetails userDetails = checkerUserDetailsService.loadUserByUsername(login);
        String jwtToken = jwtService.generateToken(userDetails);
        CheckerUserDetails checkerUserDetails = (CheckerUserDetails) userDetails;
        return new JwtAuthenticationResponse(
                jwtToken,
                new UserData(
                        checkerUserDetails.getName(),
                        checkerUserDetails.getUsername(),
                        (checkerUserDetails.getRole().equals("ADMIN") ? "admin" :
                                (checkerUserDetails.getRole().equals("organizer") ? "organizer" : "user")),
                        checkerUserDetails.getPhone()));
    }

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        userService.save(request, "USER");
        return getJwtAuthenticationResponse(request.getEmail());
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));
        return getJwtAuthenticationResponse(request.getLogin());
    }
}

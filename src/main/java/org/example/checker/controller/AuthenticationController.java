package org.example.checker.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.checker.dto.adjacent.JwtAuthenticationResponse;
import org.example.checker.dto.adjacent.SignInRequest;
import org.example.checker.dto.adjacent.SignUpRequest;
import org.example.checker.service.web.AuthenticationService;
import org.example.checker.service.web.CookieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final CookieService cookieService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    CookieService cookieService) {
        this.authenticationService = authenticationService;
        this.cookieService = cookieService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequest signUpRequest,
                                         HttpServletResponse response) {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.signUp(signUpRequest);
        cookieService.setJwtToken(response, jwtAuthenticationResponse.getToken());
        return ResponseEntity.ok(null);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequest signInRequest,
                                         HttpServletResponse response) {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.signIn(signInRequest);
        cookieService.setJwtToken(response, jwtAuthenticationResponse.getToken());
        return ResponseEntity.ok(null);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletResponse response) {
        cookieService.setTokensNull(response);
        return ResponseEntity.ok(null);
    }
}

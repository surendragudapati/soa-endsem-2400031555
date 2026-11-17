
package com.soa.klef.exam.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.soa.klef.exam.entity.User;
import com.soa.klef.exam.service.AuthService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "User registration and JWT authentication")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<Map<String, Object>> registerUser(
            @Valid @RequestBody RegistrationRequest request) {

        User user = new User(
                request.name(),
                request.email(),
                request.password()
        );

        User savedUser = authService.registerUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "message", "User registered successfully",
                        "userId", savedUser.getId(),
                        "email", savedUser.getEmail()
                )
        );
    }

    @PostMapping("/login")
    @Operation(summary = "Login and generate JWT")
    public ResponseEntity<Map<String, String>> loginUser(
            @Valid @RequestBody LoginRequest request) {

        String token = authService.loginUser(
                request.email(),
                request.password()
        );

        return ResponseEntity.ok(
                Map.of(
                        "message", "Login successful",
                        "token", token
                )
        );
    }

    @GetMapping("/validate")
    @Operation(summary = "Validate the authenticated JWT")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Map<String, String>> validateToken(
            Authentication authentication) {

        return ResponseEntity.ok(
                Map.of(
                        "message", "Token is valid",
                        "email", authentication.getName()
                )
        );
    }

    public record RegistrationRequest(
            @NotBlank @Size(max = 100) String name,
            @NotBlank @Email @Size(max = 150) String email,
            @NotBlank @Size(min = 8, max = 72) String password
    ) {}

    public record LoginRequest(
            @NotBlank @Email String email,
            @NotBlank String password
    ) {}
}

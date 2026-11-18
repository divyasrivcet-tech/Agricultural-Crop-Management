package com.agriculture.cropmanagement;

import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class StaffAuthController {

    private final JdbcTemplate jdbc;
    private final PasswordEncoder encoder;

    public StaffAuthController(JdbcTemplate jdbc, PasswordEncoder encoder) {
        this.jdbc = jdbc;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> register(@RequestBody RegisterRequest request) {

        if (request.fullName() == null || request.fullName().isBlank()
                || request.username() == null || request.username().isBlank()
                || request.email() == null
                || !Pattern.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", request.email())
                || request.password() == null || request.password().length() < 8
                || request.confirmPassword() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Enter valid details. Password must contain at least 8 characters."
            );
        }

        if (!request.password().equals(request.confirmPassword())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Passwords do not match"
            );
        }

        try {
            jdbc.update(
                """
                INSERT INTO staff_users
                (full_name, username, email, password_hash)
                VALUES (?, ?, ?, ?)
                """,
                request.fullName().trim(),
                request.username().trim(),
                request.email().trim().toLowerCase(),
                encoder.encode(request.password())
            );
        } catch (org.springframework.dao.DuplicateKeyException ex) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Username or email already exists"
            );
        }

        return Map.of("message", "Staff account created successfully. Please log in.");
    }

    public record RegisterRequest(
        String fullName,
        String username,
        String email,
        String password,
        String confirmPassword
    ) {}
}

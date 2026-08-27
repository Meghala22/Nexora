package com.nexora.operations.controller;

import com.nexora.operations.domain.Role;
import com.nexora.operations.service.AuthService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/auth")
public class AuthController {
  public record LoginRequest(@Email @NotBlank String email, @NotBlank String password) {}
  public record LoginResponse(String accessToken, String tokenType, Role role, String displayName) {}
  public record RegisterRequest(
      @NotBlank String displayName,
      @Email @NotBlank String email,
      @NotBlank @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{12,}$", message = "Password must be at least 12 characters and include uppercase, lowercase, number, and special character.") String password,
      @NotBlank String confirmPassword) {
    @AssertTrue(message = "Passwords do not match.") public boolean isPasswordsMatch() { return password != null && password.equals(confirmPassword); }
  }
  private final AuthService auth;
  public AuthController(AuthService auth) { this.auth = auth; }
  @PostMapping("/login") public LoginResponse login(@jakarta.validation.Valid @RequestBody LoginRequest request) { return auth.login(request.email(), request.password()); }
  @PostMapping("/register") public LoginResponse register(@jakarta.validation.Valid @RequestBody RegisterRequest request) { return auth.register(request); }
}

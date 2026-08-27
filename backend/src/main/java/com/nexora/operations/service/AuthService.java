package com.nexora.operations.service;

import com.nexora.operations.controller.AuthController.LoginResponse;
import com.nexora.operations.controller.AuthController.RegisterRequest;
import com.nexora.operations.domain.Role;
import com.nexora.operations.domain.UserAccount;
import com.nexora.operations.repository.UserAccountRepository;
import com.nexora.operations.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
  private final UserAccountRepository users; private final PasswordEncoder passwords; private final JwtService jwt;
  public AuthService(UserAccountRepository users, PasswordEncoder passwords, JwtService jwt) { this.users = users; this.passwords = passwords; this.jwt = jwt; }
  @Transactional public LoginResponse register(RegisterRequest request) {
    String email = request.email().trim().toLowerCase();
    if (users.findByEmailIgnoreCase(email).isPresent()) throw new DuplicateUserException();
    UserAccount user = new UserAccount(); user.setDisplayName(request.displayName().trim()); user.setEmail(email); user.setPasswordHash(passwords.encode(request.password())); user.setRole(Role.AUDITOR);
    return response(users.save(user));
  }
  @Transactional(readOnly = true) public LoginResponse login(String email, String password) {
    UserAccount user = users.findByEmailIgnoreCase(email.trim()).orElseThrow(InvalidCredentialsException::new);
    if (!passwords.matches(password, user.getPasswordHash())) throw new InvalidCredentialsException();
    return response(user);
  }
  private LoginResponse response(UserAccount user) { return new LoginResponse(jwt.issue(user), "Bearer", user.getRole(), user.getDisplayName()); }
  public static class DuplicateUserException extends RuntimeException { }
  public static class InvalidCredentialsException extends RuntimeException { }
}

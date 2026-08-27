package com.nexora.operations.config;

import com.nexora.operations.domain.Role;
import com.nexora.operations.domain.UserAccount;
import com.nexora.operations.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class DemoUserSeeder {
  @Bean CommandLineRunner seedDemoUsers(UserAccountRepository users, PasswordEncoder passwords) {
    return args -> { if (users.count() == 0) { seed(users, passwords, "Alex Morgan", "alex.morgan@nexora.demo", Role.RELATIONSHIP_MANAGER); seed(users, passwords, "Sam Taylor", "sam.taylor@nexora.demo", Role.OPERATIONS_MAKER); seed(users, passwords, "Jordan Lee", "jordan.lee@nexora.demo", Role.APPROVER); seed(users, passwords, "Nexora Admin", "admin@nexora.demo", Role.ADMIN); seed(users, passwords, "Audit User", "audit@nexora.demo", Role.AUDITOR); } };
  }
  private void seed(UserAccountRepository users, PasswordEncoder passwords, String name, String email, Role role) { UserAccount user = new UserAccount(); user.setDisplayName(name); user.setEmail(email); user.setPasswordHash(passwords.encode("Demo@12345")); user.setRole(role); users.save(user); }
}

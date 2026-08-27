package com.nexora.operations.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "users")
public class UserAccount {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private String id;
  @Column(nullable = false) private String displayName;
  @Column(nullable = false, unique = true) private String email;
  @Column(nullable = false) private String passwordHash;
  @Enumerated(EnumType.STRING) @Column(nullable = false) private Role role;
  @Column(nullable = false) private Instant createdAt = Instant.now();
  public String getId() { return id; }
  public String getDisplayName() { return displayName; } public void setDisplayName(String value) { displayName = value; }
  public String getEmail() { return email; } public void setEmail(String value) { email = value; }
  public String getPasswordHash() { return passwordHash; } public void setPasswordHash(String value) { passwordHash = value; }
  public Role getRole() { return role; } public void setRole(Role value) { role = value; }
}

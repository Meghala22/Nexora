package com.nexora.operations.security;

import com.nexora.operations.domain.UserAccount;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
  private final SecretKey key;
  private final long expirationSeconds;
  public JwtService(@Value("${nexora.security.jwt-secret}") String secret, @Value("${nexora.security.jwt-expiration-seconds:3600}") long expirationSeconds) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); this.expirationSeconds = expirationSeconds;
  }
  public String issue(UserAccount user) {
    Instant now = Instant.now();
    return Jwts.builder().subject(user.getEmail()).claim("role", user.getRole().name()).claim("name", user.getDisplayName()).issuedAt(Date.from(now)).expiration(Date.from(now.plusSeconds(expirationSeconds))).signWith(key).compact();
  }
  public Claims parse(String token) { return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload(); }
}

package com.nexora.operations.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name = "entitlement_requests")
public class EntitlementRequest {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private String id;
  @Version private long version;
  @Column(nullable = false) private String customerId;
  @Column(nullable = false) private String product;
  @Column(nullable = false) private String permission;
  @Column(nullable = false, columnDefinition = "text") private String justification;
  @Column(nullable = false) private String requestedBy;
  @Column(nullable = false) private Instant requestedAt = Instant.now();
  @Enumerated(EnumType.STRING) @Column(nullable = false) private EntitlementStatus status;
  @Column(nullable = false) private String riskLevel;
  @Column(nullable = false) private String correlationId;
  public String getId() { return id; } public long getVersion() { return version; }
  public String getCustomerId() { return customerId; } public void setCustomerId(String value) { customerId = value; }
  public String getProduct() { return product; } public void setProduct(String value) { product = value; }
  public String getPermission() { return permission; } public void setPermission(String value) { permission = value; }
  public String getJustification() { return justification; } public void setJustification(String value) { justification = value; }
  public String getRequestedBy() { return requestedBy; } public void setRequestedBy(String value) { requestedBy = value; }
  public Instant getRequestedAt() { return requestedAt; }
  public EntitlementStatus getStatus() { return status; } public void setStatus(EntitlementStatus value) { status = value; }
  public String getRiskLevel() { return riskLevel; } public void setRiskLevel(String value) { riskLevel = value; }
  public String getCorrelationId() { return correlationId; } public void setCorrelationId(String value) { correlationId = value; }
}

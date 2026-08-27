package com.nexora.operations.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "customers")
public class Customer {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private String id;
  @Version private long version;
  @Column(nullable = false) private String legalName;
  @Column(nullable = false, unique = true) private String customerNumber;
  private String industry; private String riskLevel; private String onboardingStage; private Instant updatedAt = Instant.now();
  public String getId() { return id; } public long getVersion() { return version; }
  public String getLegalName() { return legalName; } public void setLegalName(String value) { legalName = value; }
  public String getCustomerNumber() { return customerNumber; } public void setCustomerNumber(String value) { customerNumber = value; }
  public String getIndustry() { return industry; } public void setIndustry(String value) { industry = value; }
  public String getRiskLevel() { return riskLevel; } public void setRiskLevel(String value) { riskLevel = value; }
  public String getOnboardingStage() { return onboardingStage; } public void setOnboardingStage(String value) { onboardingStage = value; }
  public Instant getUpdatedAt() { return updatedAt; } public void setUpdatedAt(Instant value) { updatedAt = value; }
}


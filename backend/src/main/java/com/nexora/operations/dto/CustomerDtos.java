package com.nexora.operations.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.Instant;

public final class CustomerDtos {
  private CustomerDtos() {}
  public record CustomerResponse(String id, long version, String customerNumber, String legalName, String industry, String riskLevel, String onboardingStage, Instant updatedAt) {}
  public record CreateCustomerRequest(@NotBlank(message = "Customer legal name is required.") String legalName, @NotBlank @Pattern(regexp = "CUST-[0-9]{6}", message = "Customer number must follow CUST-000000.") String customerNumber, @NotBlank String industry) {}
  public record UpdateCustomerRequest(@NotBlank String legalName, @NotBlank String industry, @NotBlank String riskLevel, @NotBlank String onboardingStage, long version) {}
}


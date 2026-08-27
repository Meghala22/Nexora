package com.nexora.operations.dto;

import com.nexora.operations.domain.EntitlementStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

public final class EntitlementDtos {
  private EntitlementDtos() { }
  public record CreateRequest(@NotBlank String customerId, @NotBlank String product, @NotBlank String permission, @NotBlank @Size(min = 20, max = 2000) String justification) { }
  public record DecisionRequest(@NotBlank @Size(max = 1000) String comment) { }
  public record ApprovalActionResponse(String actor, String action, String comment, Instant occurredAt) { }
  public record EntitlementRequestResponse(String id, long version, String customerId, String product, String permission, String justification, String requestedBy, Instant requestedAt, EntitlementStatus status, String riskLevel, String correlationId, List<ApprovalActionResponse> approvals) { }
}

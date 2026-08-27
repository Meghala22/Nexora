package com.nexora.operations.dto;

import java.time.Instant;

public final class AuditDtos {
  private AuditDtos() { }
  public record AuditEventResponse(String actor, String actorRole, String action, String entityType, String entityId, String beforeState, String afterState, String correlationId, Instant occurredAt) { }
  public record EventMonitorResponse(String eventId, String eventType, String entityId, String correlationId, String status, Instant processedAt) { }
}

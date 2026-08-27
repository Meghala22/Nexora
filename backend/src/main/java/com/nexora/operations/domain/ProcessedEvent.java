package com.nexora.operations.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name = "processed_events")
public class ProcessedEvent {
  @Id private String eventId;
  @Column(nullable = false) private String eventType; @Column(nullable = false) private String correlationId; @Column(nullable = false) private String entityId;
  @Column(nullable = false) private Instant processedAt = Instant.now(); @Column(nullable = false) private String status;
  public String getEventId() { return eventId; } public void setEventId(String value) { eventId = value; }
  public String getEventType() { return eventType; } public void setEventType(String value) { eventType = value; }
  public String getCorrelationId() { return correlationId; } public void setCorrelationId(String value) { correlationId = value; }
  public String getEntityId() { return entityId; } public void setEntityId(String value) { entityId = value; }
  public Instant getProcessedAt() { return processedAt; } public String getStatus() { return status; } public void setStatus(String value) { status = value; }
}

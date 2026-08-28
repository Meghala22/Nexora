package com.nexora.operations.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "event_failures")
public class EventFailure {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private String id;
  @Column(nullable = false, unique = true) private String eventId;
  @Column(nullable = false) private String eventType;
  @Column(nullable = false) private String entityId;
  @Column(nullable = false) private String correlationId;
  @Column(nullable = false, columnDefinition = "text") private String errorReason;
  @Column(nullable = false) private int retryCount;
  @Column(nullable = false) private Instant lastAttemptAt = Instant.now();
  @Column(nullable = false, columnDefinition = "text") private String payload;
  @Column(nullable = false) private String status;
  public String getId() { return id; }
  public int getRetryCount() { return retryCount; }
  public void setEventId(String value) { eventId = value; }
  public void setEventType(String value) { eventType = value; }
  public void setEntityId(String value) { entityId = value; }
  public void setCorrelationId(String value) { correlationId = value; }
  public void setErrorReason(String value) { errorReason = value; }
  public void setRetryCount(int value) { retryCount = value; }
  public void setLastAttemptAt(Instant value) { lastAttemptAt = value; }
  public void setPayload(String value) { payload = value; }
  public void setStatus(String value) { status = value; }
}

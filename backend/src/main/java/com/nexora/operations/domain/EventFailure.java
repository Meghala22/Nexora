package com.nexora.operations.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "event_failures")
public class EventFailure {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private String id;
  @Column(nullable = false) private String eventId;
  private String eventType;
  @Column(columnDefinition = "text") private String errorMessage;
  private int attempts;
  private Instant failedAt = Instant.now();
  public String getId() { return id; }
  public void setEventId(String value) { eventId = value; }
  public void setEventType(String value) { eventType = value; }
  public void setErrorMessage(String value) { errorMessage = value; }
  public void setAttempts(int value) { attempts = value; }
}

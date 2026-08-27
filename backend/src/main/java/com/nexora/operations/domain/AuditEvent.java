package com.nexora.operations.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name = "audit_events")
public class AuditEvent {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  @Column(nullable = false) private String actor; @Column(nullable = false) private String actorRole;
  @Column(nullable = false) private String action; @Column(nullable = false) private String entityType; @Column(nullable = false) private String entityId;
  @Column(columnDefinition = "text") private String beforeState; @Column(columnDefinition = "text") private String afterState;
  @Column(nullable = false) private String correlationId; @Column(nullable = false) private Instant occurredAt = Instant.now();
  public String getActor() { return actor; } public void setActor(String value) { actor = value; }
  public String getActorRole() { return actorRole; } public void setActorRole(String value) { actorRole = value; }
  public String getAction() { return action; } public void setAction(String value) { action = value; }
  public String getEntityType() { return entityType; } public void setEntityType(String value) { entityType = value; }
  public String getEntityId() { return entityId; } public void setEntityId(String value) { entityId = value; }
  public String getBeforeState() { return beforeState; } public void setBeforeState(String value) { beforeState = value; }
  public String getAfterState() { return afterState; } public void setAfterState(String value) { afterState = value; }
  public String getCorrelationId() { return correlationId; } public void setCorrelationId(String value) { correlationId = value; }
  public Instant getOccurredAt() { return occurredAt; }
}

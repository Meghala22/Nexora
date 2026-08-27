package com.nexora.operations.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name = "approval_actions")
public class ApprovalAction {
  @Id @GeneratedValue(strategy = GenerationType.UUID) private String id;
  @Column(nullable = false) private String requestId;
  @Column(nullable = false) private String actor;
  @Column(nullable = false) private String action;
  @Column(columnDefinition = "text") private String comment;
  @Column(nullable = false) private Instant occurredAt = Instant.now();
  public String getId() { return id; } public String getRequestId() { return requestId; } public void setRequestId(String value) { requestId = value; }
  public String getActor() { return actor; } public void setActor(String value) { actor = value; }
  public String getAction() { return action; } public void setAction(String value) { action = value; }
  public String getComment() { return comment; } public void setComment(String value) { comment = value; }
  public Instant getOccurredAt() { return occurredAt; }
}

package com.nexora.operations.service;

import com.nexora.operations.domain.AuditEvent;
import com.nexora.operations.repository.AuditEventRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

@Service
public class AuditService {
  private final AuditEventRepository events;
  public AuditService(AuditEventRepository events) { this.events = events; }
  @Transactional public void record(String action, String entityType, String entityId, String correlationId, Map<String, ?> before, Map<String, ?> after) {
    AuditEvent event = new AuditEvent(); event.setActor(actor()); event.setActorRole(role()); event.setAction(action); event.setEntityType(entityType); event.setEntityId(entityId); event.setCorrelationId(correlationId); event.setBeforeState(before.toString()); event.setAfterState(after.toString()); events.save(event);
  }
  private String actor() { var auth = SecurityContextHolder.getContext().getAuthentication(); return auth == null ? "SYSTEM" : auth.getName(); }
  private String role() { var auth = SecurityContextHolder.getContext().getAuthentication(); return auth == null || auth.getAuthorities().isEmpty() ? "SYSTEM" : auth.getAuthorities().iterator().next().getAuthority().replace("ROLE_", ""); }
}

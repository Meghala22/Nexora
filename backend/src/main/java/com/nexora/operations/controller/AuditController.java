package com.nexora.operations.controller;

import com.nexora.operations.dto.AuditDtos.AuditEventResponse;
import com.nexora.operations.repository.AuditEventRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/v1/audit-events")
public class AuditController {
  private final AuditEventRepository events;
  public AuditController(AuditEventRepository events) { this.events = events; }
  @GetMapping @PreAuthorize("hasAnyRole('RELATIONSHIP_MANAGER','OPERATIONS_MAKER','APPROVER','ADMIN','AUDITOR')") public List<AuditEventResponse> list(@RequestParam(required = false) String entityId) {
    var records = entityId == null ? events.findTop100ByOrderByOccurredAtDesc() : events.findTop100ByEntityIdOrderByOccurredAtDesc(entityId);
    return records.stream().map(event -> new AuditEventResponse(event.getActor(), event.getActorRole(), event.getAction(), event.getEntityType(), event.getEntityId(), event.getBeforeState(), event.getAfterState(), event.getCorrelationId(), event.getOccurredAt())).toList();
  }
}

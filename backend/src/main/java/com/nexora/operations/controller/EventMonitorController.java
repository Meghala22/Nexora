package com.nexora.operations.controller;

import com.nexora.operations.dto.AuditDtos.EventMonitorResponse;
import com.nexora.operations.repository.ProcessedEventRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/v1/events")
public class EventMonitorController {
  private final ProcessedEventRepository events;
  public EventMonitorController(ProcessedEventRepository events) { this.events = events; }
  @GetMapping @PreAuthorize("hasRole('ADMIN')") public List<EventMonitorResponse> list() { return events.findTop100ByOrderByProcessedAtDesc().stream().map(event -> new EventMonitorResponse(event.getEventId(), event.getEventType(), event.getEntityId(), event.getCorrelationId(), event.getStatus(), event.getProcessedAt())).toList(); }
}

package com.nexora.operations.event;

import com.nexora.operations.domain.ProcessedEvent;
import com.nexora.operations.repository.ProcessedEventRepository;
import com.nexora.operations.service.EventFailureService;
import com.nexora.operations.service.EntitlementService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EntitlementEventConsumer {
  private final ProcessedEventRepository processed; private final EventFailureService failures; private final EntitlementService entitlements;
  public EntitlementEventConsumer(ProcessedEventRepository processed, EventFailureService failures, EntitlementService entitlements) { this.processed = processed; this.failures = failures; this.entitlements = entitlements; }
  @KafkaListener(topics = "entitlement-events", groupId = "entitlement-consumer", containerFactory = "kafkaListenerContainerFactory", autoStartup = "${nexora.events.enabled:false}")
  @Transactional public void consume(DomainEvent event) {
    try {
      if (processed.existsById(event.eventId())) return;
      if ("ENTITLEMENT_APPROVED".equals(event.eventType())) entitlements.activateFromEvent(event.entityId(), event.correlationId());
      ProcessedEvent result = new ProcessedEvent(); result.setEventId(event.eventId()); result.setEventType(event.eventType()); result.setEntityId(event.entityId()); result.setCorrelationId(event.correlationId()); result.setStatus("PROCESSED"); processed.save(result);
    } catch (RuntimeException exception) {
      failures.record(event, exception);
      throw exception;
    }
  }
}

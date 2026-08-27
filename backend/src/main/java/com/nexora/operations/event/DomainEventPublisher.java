package com.nexora.operations.event;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
public class DomainEventPublisher {
  private final KafkaTemplate<String, DomainEvent> kafka; private final boolean enabled;
  public DomainEventPublisher(KafkaTemplate<String, DomainEvent> kafka, @Value("${nexora.events.enabled:true}") boolean enabled) { this.kafka = kafka; this.enabled = enabled; }
  public DomainEvent publish(String topic, String type, String entityId, long version, String correlationId, Map<String, Object> payload) {
    DomainEvent event = new DomainEvent(UUID.randomUUID().toString(), type, Instant.now(), correlationId, entityId, version, payload);
    if (enabled) kafka.send(topic, entityId, event);
    return event;
  }
}

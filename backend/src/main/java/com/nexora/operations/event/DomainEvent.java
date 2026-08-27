package com.nexora.operations.event;

import java.time.Instant;
import java.util.Map;

/** Common envelope makes event tracing and idempotent handling consistent across topics. */
public record DomainEvent(String eventId, String eventType, Instant timestamp, String correlationId, String entityId, long version, Map<String, Object> payload) {}


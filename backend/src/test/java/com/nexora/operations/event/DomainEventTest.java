package com.nexora.operations.event;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DomainEventTest {
  @Test void retainsTraceabilityFields() {
    DomainEvent event = new DomainEvent("EVT-1", "ENTITLEMENT_APPROVED", Instant.now(), "CORR-1", "CUST-1", 2, Map.of("permission", "RELEASE"));
    assertEquals("CORR-1", event.correlationId());
    assertEquals(2, event.version());
  }
}

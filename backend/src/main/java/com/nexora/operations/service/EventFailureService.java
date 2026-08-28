package com.nexora.operations.service;

import com.nexora.operations.domain.EventFailure;
import com.nexora.operations.event.DomainEvent;
import com.nexora.operations.repository.EventFailureRepository;
import java.time.Instant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventFailureService {
  private final EventFailureRepository failures;
  public EventFailureService(EventFailureRepository failures) { this.failures = failures; }

  /** Persists independently so the diagnostic survives the failed consumer transaction. */
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void record(DomainEvent event, RuntimeException exception) {
    String reason = exception.getMessage() == null ? exception.getClass().getSimpleName() : exception.getMessage();
    EventFailure failure = failures.findByEventId(event.eventId()).orElseGet(EventFailure::new);
    int nextRetryCount = failure.getRetryCount() + 1;
    failure.setEventId(event.eventId());
    failure.setEventType(event.eventType());
    failure.setEntityId(event.entityId());
    failure.setCorrelationId(event.correlationId());
    failure.setErrorReason(reason);
    failure.setRetryCount(nextRetryCount);
    failure.setLastAttemptAt(Instant.now());
    failure.setPayload(String.valueOf(event.payload()));
    failure.setStatus("FAILED");
    failures.save(failure);
  }
}

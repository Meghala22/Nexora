package com.nexora.operations.service;

import com.nexora.operations.domain.EventFailure;
import com.nexora.operations.event.DomainEvent;
import com.nexora.operations.repository.EventFailureRepository;
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
    EventFailure failure = new EventFailure();
    failure.setEventId(event.eventId());
    failure.setEventType(event.eventType());
    failure.setErrorMessage(exception.getMessage());
    failure.setAttempts(1);
    failures.save(failure);
  }
}

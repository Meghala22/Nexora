package com.nexora.operations.repository;

import com.nexora.operations.domain.EventFailure;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EventFailureRepository extends JpaRepository<EventFailure, String> {
  Optional<EventFailure> findByEventId(String eventId);
}

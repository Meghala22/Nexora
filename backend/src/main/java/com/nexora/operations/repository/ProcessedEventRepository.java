package com.nexora.operations.repository;

import com.nexora.operations.domain.ProcessedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEvent, String> { List<ProcessedEvent> findTop100ByOrderByProcessedAtDesc(); }

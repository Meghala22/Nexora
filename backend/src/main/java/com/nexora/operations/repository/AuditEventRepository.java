package com.nexora.operations.repository;

import com.nexora.operations.domain.AuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuditEventRepository extends JpaRepository<AuditEvent, Long> { List<AuditEvent> findTop100ByOrderByOccurredAtDesc(); List<AuditEvent> findTop100ByEntityIdOrderByOccurredAtDesc(String entityId); }

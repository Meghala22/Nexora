package com.nexora.operations.repository;

import com.nexora.operations.domain.ApprovalAction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApprovalActionRepository extends JpaRepository<ApprovalAction, String> { List<ApprovalAction> findByRequestIdOrderByOccurredAtAsc(String requestId); }

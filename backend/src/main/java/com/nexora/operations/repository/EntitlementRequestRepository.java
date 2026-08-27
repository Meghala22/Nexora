package com.nexora.operations.repository;

import com.nexora.operations.domain.EntitlementRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EntitlementRequestRepository extends JpaRepository<EntitlementRequest, String> { List<EntitlementRequest> findAllByOrderByRequestedAtDesc(); List<EntitlementRequest> findByCustomerIdOrderByRequestedAtDesc(String customerId); }

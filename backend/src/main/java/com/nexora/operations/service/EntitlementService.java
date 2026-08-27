package com.nexora.operations.service;

import com.nexora.operations.domain.*;
import com.nexora.operations.dto.EntitlementDtos.*;
import com.nexora.operations.event.DomainEventPublisher;
import com.nexora.operations.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class EntitlementService {
  private final EntitlementRequestRepository requests; private final ApprovalActionRepository actions; private final CustomerRepository customers; private final AuditService audit; private final DomainEventPublisher publisher;
  public EntitlementService(EntitlementRequestRepository requests, ApprovalActionRepository actions, CustomerRepository customers, AuditService audit, DomainEventPublisher publisher) { this.requests = requests; this.actions = actions; this.customers = customers; this.audit = audit; this.publisher = publisher; }
  @Transactional public EntitlementRequestResponse create(CreateRequest input) {
    if (!customers.existsById(input.customerId())) throw new CustomerService.NotFoundException("Customer not found.");
    EntitlementRequest request = new EntitlementRequest(); request.setCustomerId(input.customerId()); request.setProduct(input.product()); request.setPermission(input.permission()); request.setJustification(input.justification()); request.setRequestedBy(actor()); request.setStatus(EntitlementStatus.PENDING_APPROVAL); request.setRiskLevel(isHighRisk(input.permission()) ? "HIGH" : "MEDIUM"); request.setCorrelationId(UUID.randomUUID().toString());
    request = requests.save(request); audit.record("ENTITLEMENT_REQUESTED", "ENTITLEMENT_REQUEST", request.getId(), request.getCorrelationId(), Map.of(), state(request)); publisher.publish("entitlement-events", "ENTITLEMENT_REQUESTED", request.getId(), request.getVersion(), request.getCorrelationId(), state(request)); return response(request);
  }
  @Transactional(readOnly = true) public List<EntitlementRequestResponse> list() { return requests.findAllByOrderByRequestedAtDesc().stream().map(this::response).toList(); }
  @Transactional(readOnly = true) public EntitlementRequestResponse find(String id) { return response(get(id)); }
  @Transactional public EntitlementRequestResponse approve(String id, DecisionRequest decision) { return decide(id, decision, EntitlementStatus.APPROVED, "APPROVED", "ENTITLEMENT_APPROVED"); }
  @Transactional public EntitlementRequestResponse reject(String id, DecisionRequest decision) { return decide(id, decision, EntitlementStatus.REJECTED, "REJECTED", "ENTITLEMENT_REJECTED"); }
  private EntitlementRequestResponse decide(String id, DecisionRequest decision, EntitlementStatus target, String action, String eventType) {
    EntitlementRequest request = get(id); if (request.getStatus() != EntitlementStatus.PENDING_APPROVAL) throw new IllegalStateException("This request has already been decided."); if (request.getRequestedBy().equalsIgnoreCase(actor())) throw new IllegalStateException("A requestor cannot approve their own entitlement request.");
    Map<String, Object> before = state(request); request.setStatus(target); ApprovalAction approval = new ApprovalAction(); approval.setRequestId(request.getId()); approval.setActor(actor()); approval.setAction(action); approval.setComment(decision.comment()); actions.save(approval); audit.record("ENTITLEMENT_" + action, "ENTITLEMENT_REQUEST", request.getId(), request.getCorrelationId(), before, state(request)); publisher.publish("entitlement-events", eventType, request.getId(), request.getVersion(), request.getCorrelationId(), state(request)); return response(request);
  }
  @Transactional public void activateFromEvent(String requestId, String correlationId) { EntitlementRequest request = get(requestId); if (request.getStatus() == EntitlementStatus.APPROVED) { Map<String,Object> before = state(request); request.setStatus(EntitlementStatus.ACTIVATED); audit.record("ENTITLEMENT_ACTIVATED", "ENTITLEMENT_REQUEST", requestId, correlationId, before, state(request)); } }
  private EntitlementRequest get(String id) { return requests.findById(id).orElseThrow(() -> new CustomerService.NotFoundException("Entitlement request not found.")); }
  private EntitlementRequestResponse response(EntitlementRequest request) { List<ApprovalActionResponse> history = actions.findByRequestIdOrderByOccurredAtAsc(request.getId()).stream().map(a -> new ApprovalActionResponse(a.getActor(), a.getAction(), a.getComment(), a.getOccurredAt())).toList(); return new EntitlementRequestResponse(request.getId(), request.getVersion(), request.getCustomerId(), request.getProduct(), request.getPermission(), request.getJustification(), request.getRequestedBy(), request.getRequestedAt(), request.getStatus(), request.getRiskLevel(), request.getCorrelationId(), history); }
  private boolean isHighRisk(String permission) { String value = permission.toLowerCase(); return value.contains("release") || value.contains("approve") || value.contains("admin"); }
  private String actor() { return SecurityContextHolder.getContext().getAuthentication().getName(); }
  private Map<String,Object> state(EntitlementRequest request) { return Map.of("status", request.getStatus().name(), "product", request.getProduct(), "permission", request.getPermission(), "risk", request.getRiskLevel()); }
}

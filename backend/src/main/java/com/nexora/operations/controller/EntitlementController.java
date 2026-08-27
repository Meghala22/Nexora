package com.nexora.operations.controller;

import com.nexora.operations.dto.EntitlementDtos.*;
import com.nexora.operations.service.EntitlementService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/v1/entitlement-requests")
public class EntitlementController {
  private final EntitlementService service;
  public EntitlementController(EntitlementService service) { this.service = service; }
  @PostMapping @PreAuthorize("hasAnyRole('OPERATIONS_MAKER','ADMIN')") public EntitlementRequestResponse create(@Valid @RequestBody CreateRequest request) { return service.create(request); }
  @GetMapping @PreAuthorize("hasAnyRole('OPERATIONS_MAKER','APPROVER','ADMIN')") public List<EntitlementRequestResponse> list() { return service.list(); }
  @GetMapping("/{id}") @PreAuthorize("hasAnyRole('OPERATIONS_MAKER','APPROVER','ADMIN')") public EntitlementRequestResponse find(@PathVariable String id) { return service.find(id); }
  @PostMapping("/{id}/approve") @PreAuthorize("hasAnyRole('APPROVER','ADMIN')") public EntitlementRequestResponse approve(@PathVariable String id, @Valid @RequestBody DecisionRequest decision) { return service.approve(id, decision); }
  @PostMapping("/{id}/reject") @PreAuthorize("hasAnyRole('APPROVER','ADMIN')") public EntitlementRequestResponse reject(@PathVariable String id, @Valid @RequestBody DecisionRequest decision) { return service.reject(id, decision); }
}

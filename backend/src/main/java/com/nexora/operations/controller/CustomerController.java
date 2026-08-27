package com.nexora.operations.controller;

import com.nexora.operations.dto.CustomerDtos.*;
import com.nexora.operations.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/v1/customers")
public class CustomerController {
  private final CustomerService service;
  public CustomerController(CustomerService service) { this.service = service; }
  @GetMapping @PreAuthorize("hasAnyRole('RELATIONSHIP_MANAGER','OPERATIONS_MAKER','APPROVER','ADMIN','AUDITOR')") public List<CustomerResponse> list() { return service.list(); }
  @GetMapping("/{id}") @PreAuthorize("hasAnyRole('RELATIONSHIP_MANAGER','OPERATIONS_MAKER','APPROVER','ADMIN','AUDITOR')") public CustomerResponse find(@PathVariable String id) { return service.find(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) @PreAuthorize("hasAnyRole('RELATIONSHIP_MANAGER','ADMIN')") public CustomerResponse create(@Valid @RequestBody CreateCustomerRequest request) { return service.create(request); }
  @PutMapping("/{id}") @PreAuthorize("hasAnyRole('RELATIONSHIP_MANAGER','ADMIN')") public CustomerResponse update(@PathVariable String id, @Valid @RequestBody UpdateCustomerRequest request) { return service.update(id, request); }
}


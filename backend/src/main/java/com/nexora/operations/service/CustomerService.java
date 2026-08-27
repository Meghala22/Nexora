package com.nexora.operations.service;

import com.nexora.operations.domain.Customer;
import com.nexora.operations.dto.CustomerDtos.*;
import com.nexora.operations.repository.CustomerRepository;
import jakarta.persistence.OptimisticLockException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class CustomerService {
  private final CustomerRepository customers;
  public CustomerService(CustomerRepository customers) { this.customers = customers; }
  @Transactional(readOnly = true) public List<CustomerResponse> list() { return customers.findAll(Sort.by("legalName")).stream().map(this::toResponse).toList(); }
  @Transactional(readOnly = true) public CustomerResponse find(String id) { return toResponse(customers.findById(id).orElseThrow(() -> new NotFoundException("Customer not found."))); }
  @Transactional public CustomerResponse create(CreateCustomerRequest request) { Customer customer = new Customer(); customer.setLegalName(request.legalName()); customer.setCustomerNumber(request.customerNumber()); customer.setIndustry(request.industry()); customer.setRiskLevel("Medium"); customer.setOnboardingStage("Submitted"); return toResponse(customers.save(customer)); }
  @Transactional public CustomerResponse update(String id, UpdateCustomerRequest request) { Customer customer = customers.findById(id).orElseThrow(() -> new NotFoundException("Customer not found.")); if (customer.getVersion() != request.version()) throw new OptimisticLockException("Stale customer version"); customer.setLegalName(request.legalName()); customer.setIndustry(request.industry()); customer.setRiskLevel(request.riskLevel()); customer.setOnboardingStage(request.onboardingStage()); customer.setUpdatedAt(Instant.now()); return toResponse(customer); }
  private CustomerResponse toResponse(Customer c) { return new CustomerResponse(c.getId(), c.getVersion(), c.getCustomerNumber(), c.getLegalName(), c.getIndustry(), c.getRiskLevel(), c.getOnboardingStage(), c.getUpdatedAt()); }
  public static class NotFoundException extends RuntimeException { public NotFoundException(String message) { super(message); } }
}


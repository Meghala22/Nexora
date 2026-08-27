package com.nexora.operations.repository;

import com.nexora.operations.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> { Optional<Customer> findByCustomerNumber(String customerNumber); }


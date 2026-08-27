package com.nexora.operations.config;

import com.nexora.operations.domain.Customer;
import com.nexora.operations.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Local sample relationships make the development UI usable without production data. */
@Configuration
class DemoCustomerSeeder {
  @Bean CommandLineRunner seedDemoCustomers(CustomerRepository customers) {
    return args -> {
      if (customers.count() == 0) {
        seed(customers, "CUST-001248", "ACME Corporation", "Industrial Manufacturing", "Medium", "Entitlements");
        seed(customers, "CUST-001315", "Globex Industries", "Energy & Utilities", "High", "KYC Review");
        seed(customers, "CUST-001102", "Stark Industries", "Technology", "Low", "Activated");
        seed(customers, "CUST-001289", "Wayne Enterprises", "Financial Services", "Medium", "Approval");
        seed(customers, "CUST-001074", "Umbrella Holdings", "Healthcare", "High", "Due Diligence");
        seed(customers, "CUST-001196", "Initech", "Business Services", "Low", "Product Setup");
      }
    };
  }

  private void seed(CustomerRepository customers, String number, String name, String industry, String risk, String stage) {
    Customer customer = new Customer();
    customer.setCustomerNumber(number);
    customer.setLegalName(name);
    customer.setIndustry(industry);
    customer.setRiskLevel(risk);
    customer.setOnboardingStage(stage);
    customers.save(customer);
  }
}

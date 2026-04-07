package com.enterprise.sample.rest.customer;

import com.enterprise.pact.framework.annotation.AutoGeneratePact;
import com.enterprise.pact.framework.annotation.PactScenario;
import com.enterprise.sample.rest.customer.dto.CreateCustomerRequest;
import com.enterprise.sample.rest.customer.dto.CustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@AutoGeneratePact(consumer = "consumer-driven-contract-testing", provider = "pact-rest-h2-sample")
public class CustomerController {
    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    @PactScenario(value = "read-customer-by-id", providerState = "customer exists in H2")
    public ResponseEntity<CustomerResponse> findById(@PathVariable UUID id, @RequestParam(defaultValue = "true") boolean includeLinks) {
        return repository.findById(id)
                .map(customer -> ResponseEntity.ok(toResponse(customer, includeLinks)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PactScenario(value = "create-customer", providerState = "customer email is unique")
    public CustomerResponse create(@RequestBody CreateCustomerRequest request) {
        CustomerEntity entity = new CustomerEntity(UUID.randomUUID(), request.name(), request.email(), request.tier(), LocalDate.now());
        repository.save(entity);
        return toResponse(entity, true);
    }

    private CustomerResponse toResponse(CustomerEntity customer, boolean includeLinks) {
        Map<String, String> links = includeLinks ? Map.of("self", "/api/customers/" + customer.getId()) : Map.of();
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail(), customer.getTier(), customer.getJoinedOn(), links);
    }
}

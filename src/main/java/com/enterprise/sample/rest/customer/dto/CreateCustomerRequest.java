package com.enterprise.sample.rest.customer.dto;

import com.enterprise.pact.framework.annotation.PactFieldExample;
import com.enterprise.pact.framework.annotation.PactSensitiveField;

public record CreateCustomerRequest(
        @PactFieldExample("Priya Customer") String name,
        @PactSensitiveField String email,
        @PactFieldExample("GOLD") String tier) {
}

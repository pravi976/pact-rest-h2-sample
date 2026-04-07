package com.enterprise.sample.rest.customer.dto;

import com.enterprise.pact.framework.annotation.PactFieldExample;
import com.enterprise.pact.framework.annotation.PactSensitiveField;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        @PactFieldExample("Priya Customer") String name,
        @PactSensitiveField String email,
        @PactFieldExample("GOLD") String tier,
        LocalDate joinedOn,
        Map<String, String> links) {
}

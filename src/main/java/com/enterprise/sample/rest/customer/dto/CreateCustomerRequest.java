package com.enterprise.sample.rest.customer.dto;

import com.fedex.cdc.automation.annotations.PactExample;
import com.fedex.cdc.automation.annotations.PactSecret;

public record CreateCustomerRequest(
        @PactExample("Priya Customer") String name,
        @PactSecret String email,
        @PactExample("GOLD") String tier) {
}

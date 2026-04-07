package com.enterprise.sample.rest.contracts;

import com.enterprise.sample.rest.customer.dto.CreateCustomerRequest;
import com.enterprise.sample.rest.customer.dto.CustomerResponse;
import com.fedex.cdc.automation.annotations.ConsumerPactClient;
import com.fedex.cdc.automation.annotations.ConsumerPactInteraction;
import org.springframework.stereotype.Component;

@Component
@ConsumerPactClient(consumer = "consumer-driven-contract-testing", provider = "pact-rest-h2-sample")
public class RestH2ProviderExpectations {
    @ConsumerPactInteraction(
            description = "consumer expects to read customer from REST H2 provider",
            providerState = "customer exists in H2",
            method = "GET",
            path = "/api/customers/22222222-2222-2222-2222-222222222222",
            query = {"includeLinks=true"},
            responseBody = CustomerResponse.class)
    public void readCustomerById() {
    }

    @ConsumerPactInteraction(
            description = "consumer expects to create customer in REST H2 provider",
            providerState = "customer email is unique",
            method = "POST",
            path = "/api/customers",
            responseStatus = 201,
            requestBody = CreateCustomerRequest.class,
            responseBody = CustomerResponse.class)
    public void createCustomer() {
    }
}
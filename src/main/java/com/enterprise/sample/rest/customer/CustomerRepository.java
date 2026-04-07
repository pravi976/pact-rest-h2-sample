package com.enterprise.sample.rest.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
}

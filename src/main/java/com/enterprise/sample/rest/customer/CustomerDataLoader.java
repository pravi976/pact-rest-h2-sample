package com.enterprise.sample.rest.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.UUID;

@Configuration
class CustomerDataLoader {
    @Bean
    CommandLineRunner seedCustomers(CustomerRepository repository) {
        return args -> repository.save(new CustomerEntity(
                UUID.fromString("22222222-2222-2222-2222-222222222222"),
                "Priya Customer",
                "priya.customer@example.com",
                "GOLD",
                LocalDate.of(2025, 1, 15)));
    }
}

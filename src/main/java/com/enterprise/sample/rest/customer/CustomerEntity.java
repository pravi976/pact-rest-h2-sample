package com.enterprise.sample.rest.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class CustomerEntity {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String tier;
    private LocalDate joinedOn;

    protected CustomerEntity() {}

    public CustomerEntity(UUID id, String name, String email, String tier, LocalDate joinedOn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tier = tier;
        this.joinedOn = joinedOn;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getTier() { return tier; }
    public LocalDate getJoinedOn() { return joinedOn; }
}

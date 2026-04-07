package com.enterprise.sample.rest;

import com.enterprise.pact.framework.generator.ContractGenerationOrchestrator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RestH2ContractGenerationTest {
    @Autowired ApplicationContext context;
    @Autowired ContractGenerationOrchestrator orchestrator;

    @Test
    void generatesRestContractsFromH2BackedController() throws Exception {
        List<Path> files = orchestrator.generateContracts(context);
        Path restPact = files.stream().filter(path -> path.getFileName().toString().endsWith("-rest.json")).findFirst().orElseThrow();
        String json = Files.readString(restPact);
        assertTrue(json.contains("read-customer-by-id"));
        assertTrue(json.contains("create-customer"));
        assertTrue(json.contains("\"email\" : \"********\""));
        assertTrue(json.contains("matchingRules"));
        assertTrue(json.contains("customer exists in H2"));
    }
}

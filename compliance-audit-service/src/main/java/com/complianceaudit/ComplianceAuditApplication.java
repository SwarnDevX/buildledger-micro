package com.complianceaudit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ComplianceAuditApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComplianceAuditApplication.class, args);
    }
}

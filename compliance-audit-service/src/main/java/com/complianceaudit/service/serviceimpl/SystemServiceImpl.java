package com.complianceaudit.service.serviceimpl;

import com.complianceaudit.dto.request.CreateAuditRequest;
import com.complianceaudit.dto.response.AuditResponse;
import com.complianceaudit.dto.response.ScanResponse;
import com.complianceaudit.enums.AuditScope;
import com.complianceaudit.exception.BadRequestException;
import com.complianceaudit.service.AuditService;
import com.complianceaudit.service.SystemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {

    private final AuditService auditService;
    private final RestTemplate restTemplate;

    private final String CONTRACT_SERVICE_URL = "http://contractcreation/contracts/";

    private void validateContract(Long contractId) {
        if (contractId == null) {
            throw new BadRequestException("Contract ID must not be null");
        }
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(CONTRACT_SERVICE_URL + contractId, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new BadRequestException("Invalid Contract ID: " + contractId);
            }
        } catch (HttpClientErrorException e) {
            throw new BadRequestException("Contract not found with ID: " + contractId);
        } catch (Exception e) {
            throw new RuntimeException("Error communicating with Contract Service: " + e.getMessage());
        }
    }

    @Override
    public ScanResponse runComplianceScan() {
        log.info("Running full compliance scan at {}", LocalDateTime.now());
        // Extend with real scan logic — e.g. scanning all active contracts
        return ScanResponse.builder()
                .status("COMPLETED")
                .triggeredAt(LocalDateTime.now())
                .totalContractsScanned(0)
                .violationsFound(0)
                .message("Compliance scan completed successfully.")
                .build();
    }

    @Override
    public AuditResponse triggerAudit(Long contractId) {
        if (contractId == null) {
            throw new BadRequestException("Contract ID is required to trigger an audit.");
        }
        
        validateContract(contractId);

        log.info("Triggering system audit for contractId: {}", contractId);
        CreateAuditRequest request = new CreateAuditRequest();
        request.setTitle("System-Triggered Audit for Contract #" + contractId);
        request.setScope(AuditScope.CONTRACT);
        request.setOfficerId(0L); // System officer placeholder
        request.setScheduledDate(LocalDate.now());
        request.setDescription("Automatically triggered compliance audit.");
        return auditService.createAudit(request);
    }
}

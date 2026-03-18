package com.complianceaudit.service.serviceimpl;

import com.complianceaudit.dto.response.ValidationResponse;
import com.complianceaudit.enums.ComplianceResult;
import com.complianceaudit.repository.ComplianceRecordRepository;
import com.complianceaudit.service.ComplianceValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComplianceValidationServiceImpl implements ComplianceValidationService {

    private final ComplianceRecordRepository complianceRecordRepository;

    @Override
    @Transactional(readOnly = true)
    public ValidationResponse validateContract(Long contractId) {
        log.info("Validating contract: {}", contractId);
        List<String> issues = new ArrayList<>();

        long failCount = complianceRecordRepository.countByContractIdAndResult(contractId, ComplianceResult.FAIL);
        if (failCount > 0) {
            issues.add(String.format("Contract has %d failed compliance checks.", failCount));
        }

        long pendingCount = complianceRecordRepository.countByContractIdAndResult(contractId, ComplianceResult.PENDING);
        if (pendingCount > 0) {
            issues.add(String.format("Contract has %d pending compliance checks.", pendingCount));
        }

        return ValidationResponse.builder()
                .entityType("CONTRACT")
                .entityId(contractId)
                .valid(issues.isEmpty())
                .issues(issues)
                .validatedAt(LocalDateTime.now())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ValidationResponse validateDelivery(Long deliveryId) {
        log.info("Validating delivery: {}", deliveryId);
        // Extend with delivery-specific compliance logic
        return ValidationResponse.builder()
                .entityType("DELIVERY")
                .entityId(deliveryId)
                .valid(true)
                .issues(new ArrayList<>())
                .validatedAt(LocalDateTime.now())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ValidationResponse validateInvoice(Long invoiceId) {
        log.info("Validating invoice: {}", invoiceId);
        // Extend with invoice-specific compliance logic
        return ValidationResponse.builder()
                .entityType("INVOICE")
                .entityId(invoiceId)
                .valid(true)
                .issues(new ArrayList<>())
                .validatedAt(LocalDateTime.now())
                .build();
    }
}

package com.complianceaudit.service.serviceimpl;

import com.complianceaudit.dto.response.AuditReportResponse;
import com.complianceaudit.dto.response.AuditResponse;
import com.complianceaudit.dto.response.ComplianceSummaryResponse;
import com.complianceaudit.dto.response.VendorComplianceScoreResponse;
import com.complianceaudit.enums.AuditScope;
import com.complianceaudit.enums.AuditStatus;
import com.complianceaudit.enums.ComplianceResult;
import com.complianceaudit.enums.ViolationStatus;
import com.complianceaudit.mapper.AuditMapper;
import com.complianceaudit.repository.AuditRepository;
import com.complianceaudit.repository.ComplianceRecordRepository;
import com.complianceaudit.repository.ViolationRepository;
import com.complianceaudit.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ComplianceRecordRepository complianceRecordRepository;
    private final AuditRepository auditRepository;
    private final ViolationRepository violationRepository;
    private final AuditMapper auditMapper;

    @Override
    @Transactional(readOnly = true)
    public ComplianceSummaryResponse getComplianceSummary(LocalDate fromDate, LocalDate toDate) {
        log.info("Generating compliance summary from {} to {}", fromDate, toDate);
        long total = complianceRecordRepository.countByDateRange(fromDate, toDate);
        long passed = complianceRecordRepository.countByResultAndDateRange(ComplianceResult.PASS, fromDate, toDate);
        long failed = complianceRecordRepository.countByResultAndDateRange(ComplianceResult.FAIL, fromDate, toDate);
        long pending = complianceRecordRepository.countByResultAndDateRange(ComplianceResult.PENDING, fromDate, toDate);

        long totalViolations = violationRepository.count();
        long resolvedViolations = violationRepository.countByStatus(ViolationStatus.RESOLVED);
        long openViolations = violationRepository.countByStatus(ViolationStatus.OPEN);

        double passRate = total > 0 ? (double) passed / total * 100 : 0;

        return ComplianceSummaryResponse.builder()
                .fromDate(fromDate)
                .toDate(toDate)
                .totalChecks(total)
                .passed(passed)
                .failed(failed)
                .pending(pending)
                .passRate(Math.round(passRate * 100.0) / 100.0)
                .totalViolations(totalViolations)
                .resolvedViolations(resolvedViolations)
                .openViolations(openViolations)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public AuditReportResponse getAuditReport(String scope) {
        log.info("Generating audit report for scope: {}", scope);
        AuditScope auditScope = scope != null ? AuditScope.valueOf(scope.toUpperCase()) : null;

        List<AuditResponse> audits = auditScope != null
                ? auditMapper.toResponseList(auditRepository.findByScope(auditScope))
                : auditMapper.toResponseList(auditRepository.findAll());

        return AuditReportResponse.builder()
                .scope(auditScope)
                .totalAudits(auditRepository.count())
                .openAudits(auditRepository.countByStatus(AuditStatus.OPEN))
                .closedAudits(auditRepository.countByStatus(AuditStatus.CLOSED))
                .inProgressAudits(auditRepository.countByStatus(AuditStatus.IN_PROGRESS))
                .audits(audits)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public VendorComplianceScoreResponse getVendorComplianceScore(Long vendorId) {
        log.info("Calculating compliance score for vendor: {}", vendorId);
        // In a real system, join with vendor/contract tables. Using contractId as proxy here.
        long total = complianceRecordRepository.countByContractIdAndResult(vendorId, ComplianceResult.PASS)
                + complianceRecordRepository.countByContractIdAndResult(vendorId, ComplianceResult.FAIL);
        long passed = complianceRecordRepository.countByContractIdAndResult(vendorId, ComplianceResult.PASS);
        long failed = complianceRecordRepository.countByContractIdAndResult(vendorId, ComplianceResult.FAIL);
        double score = total > 0 ? (double) passed / total * 100 : 0;

        String rating = score >= 90 ? "EXCELLENT" : score >= 75 ? "GOOD" : score >= 50 ? "FAIR" : "POOR";

        return VendorComplianceScoreResponse.builder()
                .vendorId(vendorId)
                .totalContracts(total)
                .passedContracts(passed)
                .failedContracts(failed)
                .complianceScore(Math.round(score * 100.0) / 100.0)
                .rating(rating)
                .build();
    }
}

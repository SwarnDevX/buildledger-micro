package com.complianceaudit.service;

import com.complianceaudit.dto.response.AuditReportResponse;
import com.complianceaudit.dto.response.ComplianceSummaryResponse;
import com.complianceaudit.dto.response.VendorComplianceScoreResponse;

import java.time.LocalDate;

public interface ReportService {
    ComplianceSummaryResponse getComplianceSummary(LocalDate fromDate, LocalDate toDate);
    AuditReportResponse getAuditReport(String scope);
    VendorComplianceScoreResponse getVendorComplianceScore(Long vendorId);
}

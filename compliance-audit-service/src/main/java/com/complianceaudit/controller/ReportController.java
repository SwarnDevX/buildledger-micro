package com.complianceaudit.controller;

import com.complianceaudit.dto.response.ApiResponse;
import com.complianceaudit.dto.response.AuditReportResponse;
import com.complianceaudit.dto.response.ComplianceSummaryResponse;
import com.complianceaudit.dto.response.VendorComplianceScoreResponse;
import com.complianceaudit.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService service;

    @GetMapping("/compliance-summary")
    public ResponseEntity<ApiResponse<ComplianceSummaryResponse>> getComplianceSummary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        ComplianceSummaryResponse summary = service.getComplianceSummary(fromDate, toDate);
        return ResponseEntity.ok(ApiResponse.success("Compliance summary generated.", summary));
    }

    @GetMapping("/audit")
    public ResponseEntity<ApiResponse<AuditReportResponse>> getAuditReport(
            @RequestParam(required = false) String scope) {
        AuditReportResponse report = service.getAuditReport(scope);
        return ResponseEntity.ok(ApiResponse.success("Audit report generated.", report));
    }

    @GetMapping("/vendor/{vendorId}/compliance-score")
    public ResponseEntity<ApiResponse<VendorComplianceScoreResponse>> getVendorComplianceScore(
            @PathVariable Long vendorId) {
        VendorComplianceScoreResponse score = service.getVendorComplianceScore(vendorId);
        return ResponseEntity.ok(ApiResponse.success("Vendor compliance score fetched.", score));
    }
}

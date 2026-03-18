package com.complianceaudit.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ComplianceSummaryResponse {
    private LocalDate fromDate;
    private LocalDate toDate;
    private long totalChecks;
    private long passed;
    private long failed;
    private long pending;
    private double passRate;
    private long totalViolations;
    private long resolvedViolations;
    private long openViolations;
}

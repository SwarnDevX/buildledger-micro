package com.complianceaudit.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VendorComplianceScoreResponse {
    private Long vendorId;
    private long totalContracts;
    private long passedContracts;
    private long failedContracts;
    private double complianceScore;
    private String rating;
}

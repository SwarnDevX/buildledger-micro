package com.complianceaudit.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BulkComplianceResponse {
    private int totalRequested;
    private int successCount;
    private int failureCount;
    private List<ComplianceRecordResponse> created;
    private List<String> errors;
}

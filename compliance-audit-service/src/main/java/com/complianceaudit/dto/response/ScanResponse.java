package com.complianceaudit.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ScanResponse {
    private String status;
    private LocalDateTime triggeredAt;
    private int totalContractsScanned;
    private int violationsFound;
    private String message;
}

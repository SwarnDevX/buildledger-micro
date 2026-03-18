package com.complianceaudit.dto.response;

import com.complianceaudit.enums.ComplianceResult;
import com.complianceaudit.enums.ComplianceType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ComplianceRecordResponse {
    private Long id;
    private Long contractId;
    private ComplianceType type;
    private ComplianceResult result;
    private LocalDate checkDate;
    private String remarks;
    private String checkedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

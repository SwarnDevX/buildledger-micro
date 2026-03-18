package com.complianceaudit.dto.response;

import com.complianceaudit.enums.AuditScope;
import com.complianceaudit.enums.AuditStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AuditResponse {
    private Long id;
    private String title;
    private AuditStatus status;
    private AuditScope scope;
    private Long officerId;
    private LocalDate scheduledDate;
    private LocalDate closedDate;
    private String description;
    private List<AuditEvidenceResponse> evidences;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

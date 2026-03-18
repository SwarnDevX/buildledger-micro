package com.complianceaudit.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AuditEvidenceResponse {
    private Long id;
    private Long auditId;
    private String description;
    private String fileUrl;
    private String uploadedBy;
    private LocalDateTime uploadedAt;
}

package com.complianceaudit.dto.response;

import com.complianceaudit.enums.ViolationStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViolationResponse {
    private Long id;
    private Long contractId;
    private String description;
    private ViolationStatus status;
    private String severity;
    private LocalDateTime detectedAt;
    private LocalDateTime resolvedAt;
}

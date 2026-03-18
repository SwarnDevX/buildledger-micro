package com.complianceaudit.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ValidationResponse {
    private String entityType;
    private Long entityId;
    private boolean valid;
    private List<String> issues;
    private LocalDateTime validatedAt;
}

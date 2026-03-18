package com.complianceaudit.dto.request;

import com.complianceaudit.enums.AuditScope;
import com.complianceaudit.enums.AuditStatus;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateAuditRequest {

    @Size(max = 200, message = "Title cannot exceed 200 characters")
    private String title;

    private AuditScope scope;
    private AuditStatus status;
    private Long officerId;
    private LocalDate scheduledDate;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;
}

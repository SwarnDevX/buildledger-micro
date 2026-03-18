package com.complianceaudit.dto.request;

import com.complianceaudit.enums.AuditScope;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateAuditRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title cannot exceed 200 characters")
    private String title;

    @NotNull(message = "Scope is required")
    private AuditScope scope;

    @NotNull(message = "Officer ID is required")
    private Long officerId;

    @FutureOrPresent(message = "Scheduled date must be today or in the future")
    private LocalDate scheduledDate;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;
}

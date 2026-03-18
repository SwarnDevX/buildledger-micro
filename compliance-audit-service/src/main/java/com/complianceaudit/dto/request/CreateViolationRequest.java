package com.complianceaudit.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateViolationRequest {

    private Long contractId;

    @NotBlank(message = "Violation description is required")
    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    @Size(max = 50, message = "Severity cannot exceed 50 characters")
    private String severity;
}

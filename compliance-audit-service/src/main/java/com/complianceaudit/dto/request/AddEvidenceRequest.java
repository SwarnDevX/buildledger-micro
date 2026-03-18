package com.complianceaudit.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddEvidenceRequest {

    @NotBlank(message = "Evidence description is required")
    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    private String fileUrl;

    @Size(max = 100, message = "Uploaded-by cannot exceed 100 characters")
    private String uploadedBy;
}

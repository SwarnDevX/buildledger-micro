package com.complianceaudit.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class BulkComplianceRequest {

    @NotEmpty(message = "At least one compliance record is required")
    @Valid
    private List<CreateComplianceRecordRequest> records;
}

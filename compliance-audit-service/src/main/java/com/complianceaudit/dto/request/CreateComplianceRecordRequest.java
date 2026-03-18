package com.complianceaudit.dto.request;

import com.complianceaudit.enums.ComplianceResult;
import com.complianceaudit.enums.ComplianceType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateComplianceRecordRequest {

    @NotNull(message = "Contract ID is required")
    private Long contractId;

    @NotNull(message = "Compliance type is required")
    private ComplianceType type;

    @NotNull(message = "Compliance result is required")
    private ComplianceResult result;

    @NotNull(message = "Check date is required")
    @PastOrPresent(message = "Check date cannot be in the future")
    private LocalDate checkDate;

    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    private String remarks;

    @Size(max = 100, message = "Checked-by cannot exceed 100 characters")
    private String checkedBy;
}

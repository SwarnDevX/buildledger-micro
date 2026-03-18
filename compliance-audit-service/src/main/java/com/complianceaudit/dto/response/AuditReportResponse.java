package com.complianceaudit.dto.response;

import com.complianceaudit.enums.AuditScope;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuditReportResponse {
    private AuditScope scope;
    private long totalAudits;
    private long openAudits;
    private long closedAudits;
    private long inProgressAudits;
    private List<AuditResponse> audits;
}

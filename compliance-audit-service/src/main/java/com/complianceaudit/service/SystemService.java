package com.complianceaudit.service;

import com.complianceaudit.dto.response.AuditResponse;
import com.complianceaudit.dto.response.ScanResponse;

public interface SystemService {
    ScanResponse runComplianceScan();
    AuditResponse triggerAudit(Long contractId);
}

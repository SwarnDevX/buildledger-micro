package com.complianceaudit.controller;

import com.complianceaudit.dto.response.ApiResponse;
import com.complianceaudit.dto.response.AuditResponse;
import com.complianceaudit.dto.response.ScanResponse;
import com.complianceaudit.service.SystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/system/compliance")
@RequiredArgsConstructor
public class SystemController {

    private final SystemService service;

    @PostMapping("/run-scan")
    public ResponseEntity<ApiResponse<ScanResponse>> runComplianceScan() {
        ScanResponse response = service.runComplianceScan();
        return ResponseEntity.ok(ApiResponse.success("Compliance scan triggered.", response));
    }

    @PostMapping("/trigger-audit")
    public ResponseEntity<ApiResponse<AuditResponse>> triggerAudit(
            @RequestParam Long contractId) {
        AuditResponse response = service.triggerAudit(contractId);
        return ResponseEntity.ok(ApiResponse.success("Audit triggered for contract.", response));
    }
}

package com.complianceaudit.controller;

import com.complianceaudit.dto.request.AddEvidenceRequest;
import com.complianceaudit.dto.request.CreateAuditRequest;
import com.complianceaudit.dto.request.UpdateAuditRequest;
import com.complianceaudit.dto.response.ApiResponse;
import com.complianceaudit.dto.response.AuditEvidenceResponse;
import com.complianceaudit.dto.response.AuditResponse;
import com.complianceaudit.service.AuditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/audits")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService service;

    // =========================
    // AUDITS
    // =========================

    @PostMapping
    public ResponseEntity<ApiResponse<AuditResponse>> createAudit(
            @Valid @RequestBody CreateAuditRequest request) {
        AuditResponse response = service.createAudit(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Audit created successfully.", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AuditResponse>>> getAllAudits(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String scope,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
        List<AuditResponse> audits = service.getAllAudits(status, scope, dateFrom, dateTo);
        return ResponseEntity.ok(ApiResponse.success("Audits fetched successfully.", audits));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuditResponse>> getAuditById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Audit fetched.", service.getAuditById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AuditResponse>> updateAudit(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAuditRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Audit updated.", service.updateAudit(id, request)));
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<ApiResponse<AuditResponse>> closeAudit(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Audit closed successfully.", service.closeAudit(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAudit(@PathVariable Long id) {
        service.deleteAudit(id);
        return ResponseEntity.ok(ApiResponse.success("Audit deleted."));
    }

    @GetMapping("/officer/{officerId}")
    public ResponseEntity<ApiResponse<List<AuditResponse>>> getAuditsByOfficer(@PathVariable Long officerId) {
        return ResponseEntity.ok(ApiResponse.success("Audits for officer fetched.",
                service.getAuditsByOfficer(officerId)));
    }

    // =========================
    // AUDIT EVIDENCE
    // =========================

    @PostMapping("/{auditId}/evidence")
    public ResponseEntity<ApiResponse<AuditEvidenceResponse>> addEvidence(
            @PathVariable Long auditId,
            @Valid @RequestBody AddEvidenceRequest request) {
        AuditEvidenceResponse response = service.addEvidence(auditId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Evidence added to audit.", response));
    }

    @GetMapping("/{auditId}/evidence")
    public ResponseEntity<ApiResponse<List<AuditEvidenceResponse>>> getEvidence(@PathVariable Long auditId) {
        return ResponseEntity.ok(ApiResponse.success("Evidence fetched.", service.getEvidence(auditId)));
    }
}

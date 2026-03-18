package com.complianceaudit.service;

import com.complianceaudit.dto.request.AddEvidenceRequest;
import com.complianceaudit.dto.request.CreateAuditRequest;
import com.complianceaudit.dto.request.UpdateAuditRequest;
import com.complianceaudit.dto.response.AuditEvidenceResponse;
import com.complianceaudit.dto.response.AuditResponse;

import java.time.LocalDate;
import java.util.List;

public interface AuditService {

    AuditResponse createAudit(CreateAuditRequest request);

    List<AuditResponse> getAllAudits(String status, String scope, LocalDate dateFrom, LocalDate dateTo);

    AuditResponse getAuditById(Long id);

    AuditResponse updateAudit(Long id, UpdateAuditRequest request);

    AuditResponse closeAudit(Long id);

    void deleteAudit(Long id);

    List<AuditResponse> getAuditsByOfficer(Long officerId);

    AuditEvidenceResponse addEvidence(Long auditId, AddEvidenceRequest request);

    List<AuditEvidenceResponse> getEvidence(Long auditId);
}

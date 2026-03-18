package com.complianceaudit.service.serviceimpl;

import com.complianceaudit.dto.request.AddEvidenceRequest;
import com.complianceaudit.dto.request.CreateAuditRequest;
import com.complianceaudit.dto.request.UpdateAuditRequest;
import com.complianceaudit.dto.response.AuditEvidenceResponse;
import com.complianceaudit.dto.response.AuditResponse;
import com.complianceaudit.entity.Audit;
import com.complianceaudit.entity.AuditEvidence;
import com.complianceaudit.enums.AuditScope;
import com.complianceaudit.enums.AuditStatus;
import com.complianceaudit.exception.BadRequestException;
import com.complianceaudit.exception.ResourceNotFoundException;
import com.complianceaudit.mapper.AuditEvidenceMapper;
import com.complianceaudit.mapper.AuditMapper;
import com.complianceaudit.repository.AuditEvidenceRepository;
import com.complianceaudit.repository.AuditRepository;
import com.complianceaudit.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;
    private final AuditEvidenceRepository evidenceRepository;
    private final AuditMapper auditMapper;
    private final AuditEvidenceMapper evidenceMapper;

    @Override
    @Transactional
    public AuditResponse createAudit(CreateAuditRequest request) {
        log.info("Creating audit with scope: {}", request.getScope());
        Audit audit = auditMapper.toEntity(request);
        return auditMapper.toResponse(auditRepository.save(audit));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditResponse> getAllAudits(String status, String scope, LocalDate dateFrom, LocalDate dateTo) {
        AuditStatus auditStatus = status != null ? AuditStatus.valueOf(status.toUpperCase()) : null;
        AuditScope auditScope = scope != null ? AuditScope.valueOf(scope.toUpperCase()) : null;
        return auditMapper.toResponseList(auditRepository.findByFilters(auditStatus, auditScope, dateFrom, dateTo));
    }

    @Override
    @Transactional(readOnly = true)
    public AuditResponse getAuditById(Long id) {
        return auditMapper.toResponse(findByIdOrThrow(id));
    }

    @Override
    @Transactional
    public AuditResponse updateAudit(Long id, UpdateAuditRequest request) {
        log.info("Updating audit id: {}", id);
        Audit audit = findByIdOrThrow(id);
        auditMapper.updateEntityFromRequest(audit, request);
        return auditMapper.toResponse(auditRepository.save(audit));
    }

    @Override
    @Transactional
    public AuditResponse closeAudit(Long id) {
        log.info("Closing audit id: {}", id);
        Audit audit = findByIdOrThrow(id);
        if (audit.getStatus() == AuditStatus.CLOSED) {
            throw new BadRequestException("Audit with id " + id + " is already closed.");
        }
        audit.setStatus(AuditStatus.CLOSED);
        audit.setClosedDate(LocalDate.now());
        return auditMapper.toResponse(auditRepository.save(audit));
    }

    @Override
    @Transactional
    public void deleteAudit(Long id) {
        log.info("Deleting audit id: {}", id);
        findByIdOrThrow(id);
        auditRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditResponse> getAuditsByOfficer(Long officerId) {
        return auditMapper.toResponseList(auditRepository.findByOfficerId(officerId));
    }

    @Override
    @Transactional
    public AuditEvidenceResponse addEvidence(Long auditId, AddEvidenceRequest request) {
        log.info("Adding evidence to audit id: {}", auditId);
        Audit audit = findByIdOrThrow(auditId);
        AuditEvidence evidence = evidenceMapper.toEntity(request, audit);
        return evidenceMapper.toResponse(evidenceRepository.save(evidence));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditEvidenceResponse> getEvidence(Long auditId) {
        findByIdOrThrow(auditId);
        return evidenceMapper.toResponseList(evidenceRepository.findByAuditId(auditId));
    }

    private Audit findByIdOrThrow(Long id) {
        return auditRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audit", "id", id));
    }
}

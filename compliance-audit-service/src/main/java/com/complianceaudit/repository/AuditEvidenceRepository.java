package com.complianceaudit.repository;

import com.complianceaudit.entity.AuditEvidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditEvidenceRepository extends JpaRepository<AuditEvidence, Long> {
    List<AuditEvidence> findByAuditId(Long auditId);
}

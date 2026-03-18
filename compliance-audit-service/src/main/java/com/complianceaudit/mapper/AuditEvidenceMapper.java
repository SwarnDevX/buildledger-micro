package com.complianceaudit.mapper;

import com.complianceaudit.dto.request.AddEvidenceRequest;
import com.complianceaudit.dto.response.AuditEvidenceResponse;
import com.complianceaudit.entity.Audit;
import com.complianceaudit.entity.AuditEvidence;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditEvidenceMapper {

    @Mapping(target = "audit", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uploadedAt", ignore = true)
    AuditEvidence toEntity(AddEvidenceRequest request, @Context Audit audit);

    @Mapping(target = "auditId", source = "audit.id")
    AuditEvidenceResponse toResponse(AuditEvidence entity);

    List<AuditEvidenceResponse> toResponseList(List<AuditEvidence> entities);

    @AfterMapping
    default void setAudit(@MappingTarget AuditEvidence evidence, @Context Audit audit) {
        evidence.setAudit(audit);
    }
}
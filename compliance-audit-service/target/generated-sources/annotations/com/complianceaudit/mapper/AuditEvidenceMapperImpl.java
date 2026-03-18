package com.complianceaudit.mapper;

import com.complianceaudit.dto.request.AddEvidenceRequest;
import com.complianceaudit.dto.response.AuditEvidenceResponse;
import com.complianceaudit.entity.Audit;
import com.complianceaudit.entity.AuditEvidence;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-18T10:58:50+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class AuditEvidenceMapperImpl implements AuditEvidenceMapper {

    @Override
    public AuditEvidence toEntity(AddEvidenceRequest request, Audit audit) {
        if ( request == null ) {
            return null;
        }

        AuditEvidence.AuditEvidenceBuilder auditEvidence = AuditEvidence.builder();

        auditEvidence.description( request.getDescription() );
        auditEvidence.fileUrl( request.getFileUrl() );
        auditEvidence.uploadedBy( request.getUploadedBy() );

        AuditEvidence auditEvidenceResult = auditEvidence.build();

        setAudit( auditEvidenceResult, audit );

        return auditEvidenceResult;
    }

    @Override
    public AuditEvidenceResponse toResponse(AuditEvidence entity) {
        if ( entity == null ) {
            return null;
        }

        AuditEvidenceResponse auditEvidenceResponse = new AuditEvidenceResponse();

        auditEvidenceResponse.setAuditId( entityAuditId( entity ) );
        auditEvidenceResponse.setId( entity.getId() );
        auditEvidenceResponse.setDescription( entity.getDescription() );
        auditEvidenceResponse.setFileUrl( entity.getFileUrl() );
        auditEvidenceResponse.setUploadedBy( entity.getUploadedBy() );
        auditEvidenceResponse.setUploadedAt( entity.getUploadedAt() );

        return auditEvidenceResponse;
    }

    @Override
    public List<AuditEvidenceResponse> toResponseList(List<AuditEvidence> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AuditEvidenceResponse> list = new ArrayList<AuditEvidenceResponse>( entities.size() );
        for ( AuditEvidence auditEvidence : entities ) {
            list.add( toResponse( auditEvidence ) );
        }

        return list;
    }

    private Long entityAuditId(AuditEvidence auditEvidence) {
        Audit audit = auditEvidence.getAudit();
        if ( audit == null ) {
            return null;
        }
        return audit.getId();
    }
}

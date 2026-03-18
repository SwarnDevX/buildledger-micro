package com.complianceaudit.mapper;

import com.complianceaudit.dto.request.CreateAuditRequest;
import com.complianceaudit.dto.request.UpdateAuditRequest;
import com.complianceaudit.dto.response.AuditEvidenceResponse;
import com.complianceaudit.dto.response.AuditResponse;
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
public class AuditMapperImpl implements AuditMapper {

    @Override
    public Audit toEntity(CreateAuditRequest request) {
        if ( request == null ) {
            return null;
        }

        Audit.AuditBuilder audit = Audit.builder();

        audit.title( request.getTitle() );
        audit.scope( request.getScope() );
        audit.officerId( request.getOfficerId() );
        audit.scheduledDate( request.getScheduledDate() );
        audit.description( request.getDescription() );

        audit.status( com.complianceaudit.enums.AuditStatus.OPEN );

        return audit.build();
    }

    @Override
    public AuditResponse toResponse(Audit entity) {
        if ( entity == null ) {
            return null;
        }

        AuditResponse auditResponse = new AuditResponse();

        auditResponse.setId( entity.getId() );
        auditResponse.setTitle( entity.getTitle() );
        auditResponse.setStatus( entity.getStatus() );
        auditResponse.setScope( entity.getScope() );
        auditResponse.setOfficerId( entity.getOfficerId() );
        auditResponse.setScheduledDate( entity.getScheduledDate() );
        auditResponse.setClosedDate( entity.getClosedDate() );
        auditResponse.setDescription( entity.getDescription() );
        auditResponse.setEvidences( auditEvidenceListToAuditEvidenceResponseList( entity.getEvidences() ) );
        auditResponse.setCreatedAt( entity.getCreatedAt() );
        auditResponse.setUpdatedAt( entity.getUpdatedAt() );

        return auditResponse;
    }

    @Override
    public List<AuditResponse> toResponseList(List<Audit> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AuditResponse> list = new ArrayList<AuditResponse>( entities.size() );
        for ( Audit audit : entities ) {
            list.add( toResponse( audit ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromRequest(Audit entity, UpdateAuditRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getTitle() != null ) {
            entity.setTitle( request.getTitle() );
        }
        if ( request.getStatus() != null ) {
            entity.setStatus( request.getStatus() );
        }
        if ( request.getScope() != null ) {
            entity.setScope( request.getScope() );
        }
        if ( request.getOfficerId() != null ) {
            entity.setOfficerId( request.getOfficerId() );
        }
        if ( request.getScheduledDate() != null ) {
            entity.setScheduledDate( request.getScheduledDate() );
        }
        if ( request.getDescription() != null ) {
            entity.setDescription( request.getDescription() );
        }
    }

    protected AuditEvidenceResponse auditEvidenceToAuditEvidenceResponse(AuditEvidence auditEvidence) {
        if ( auditEvidence == null ) {
            return null;
        }

        AuditEvidenceResponse auditEvidenceResponse = new AuditEvidenceResponse();

        auditEvidenceResponse.setId( auditEvidence.getId() );
        auditEvidenceResponse.setDescription( auditEvidence.getDescription() );
        auditEvidenceResponse.setFileUrl( auditEvidence.getFileUrl() );
        auditEvidenceResponse.setUploadedBy( auditEvidence.getUploadedBy() );
        auditEvidenceResponse.setUploadedAt( auditEvidence.getUploadedAt() );

        return auditEvidenceResponse;
    }

    protected List<AuditEvidenceResponse> auditEvidenceListToAuditEvidenceResponseList(List<AuditEvidence> list) {
        if ( list == null ) {
            return null;
        }

        List<AuditEvidenceResponse> list1 = new ArrayList<AuditEvidenceResponse>( list.size() );
        for ( AuditEvidence auditEvidence : list ) {
            list1.add( auditEvidenceToAuditEvidenceResponse( auditEvidence ) );
        }

        return list1;
    }
}

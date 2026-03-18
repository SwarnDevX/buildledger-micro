package com.complianceaudit.mapper;

import com.complianceaudit.dto.request.CreateViolationRequest;
import com.complianceaudit.dto.response.ViolationResponse;
import com.complianceaudit.entity.Violation;
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
public class ViolationMapperImpl implements ViolationMapper {

    @Override
    public Violation toEntity(CreateViolationRequest request) {
        if ( request == null ) {
            return null;
        }

        Violation.ViolationBuilder violation = Violation.builder();

        violation.contractId( request.getContractId() );
        violation.description( request.getDescription() );
        violation.severity( request.getSeverity() );

        violation.status( com.complianceaudit.enums.ViolationStatus.OPEN );

        return violation.build();
    }

    @Override
    public ViolationResponse toResponse(Violation entity) {
        if ( entity == null ) {
            return null;
        }

        ViolationResponse violationResponse = new ViolationResponse();

        violationResponse.setId( entity.getId() );
        violationResponse.setContractId( entity.getContractId() );
        violationResponse.setDescription( entity.getDescription() );
        violationResponse.setStatus( entity.getStatus() );
        violationResponse.setSeverity( entity.getSeverity() );
        violationResponse.setDetectedAt( entity.getDetectedAt() );
        violationResponse.setResolvedAt( entity.getResolvedAt() );

        return violationResponse;
    }

    @Override
    public List<ViolationResponse> toResponseList(List<Violation> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ViolationResponse> list = new ArrayList<ViolationResponse>( entities.size() );
        for ( Violation violation : entities ) {
            list.add( toResponse( violation ) );
        }

        return list;
    }
}

package com.complianceaudit.mapper;

import com.complianceaudit.dto.request.CreateComplianceRecordRequest;
import com.complianceaudit.dto.request.UpdateComplianceRecordRequest;
import com.complianceaudit.dto.response.ComplianceRecordResponse;
import com.complianceaudit.entity.ComplianceRecord;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-18T10:58:49+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class ComplianceRecordMapperImpl implements ComplianceRecordMapper {

    @Override
    public ComplianceRecord toEntity(CreateComplianceRecordRequest request) {
        if ( request == null ) {
            return null;
        }

        ComplianceRecord.ComplianceRecordBuilder complianceRecord = ComplianceRecord.builder();

        complianceRecord.contractId( request.getContractId() );
        complianceRecord.type( request.getType() );
        complianceRecord.result( request.getResult() );
        complianceRecord.checkDate( request.getCheckDate() );
        complianceRecord.remarks( request.getRemarks() );
        complianceRecord.checkedBy( request.getCheckedBy() );

        return complianceRecord.build();
    }

    @Override
    public ComplianceRecordResponse toResponse(ComplianceRecord entity) {
        if ( entity == null ) {
            return null;
        }

        ComplianceRecordResponse complianceRecordResponse = new ComplianceRecordResponse();

        complianceRecordResponse.setId( entity.getId() );
        complianceRecordResponse.setContractId( entity.getContractId() );
        complianceRecordResponse.setType( entity.getType() );
        complianceRecordResponse.setResult( entity.getResult() );
        complianceRecordResponse.setCheckDate( entity.getCheckDate() );
        complianceRecordResponse.setRemarks( entity.getRemarks() );
        complianceRecordResponse.setCheckedBy( entity.getCheckedBy() );
        complianceRecordResponse.setCreatedAt( entity.getCreatedAt() );
        complianceRecordResponse.setUpdatedAt( entity.getUpdatedAt() );

        return complianceRecordResponse;
    }

    @Override
    public List<ComplianceRecordResponse> toResponseList(List<ComplianceRecord> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ComplianceRecordResponse> list = new ArrayList<ComplianceRecordResponse>( entities.size() );
        for ( ComplianceRecord complianceRecord : entities ) {
            list.add( toResponse( complianceRecord ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromRequest(ComplianceRecord entity, UpdateComplianceRecordRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getResult() != null ) {
            entity.setResult( request.getResult() );
        }
        if ( request.getCheckDate() != null ) {
            entity.setCheckDate( request.getCheckDate() );
        }
        if ( request.getRemarks() != null ) {
            entity.setRemarks( request.getRemarks() );
        }
        if ( request.getCheckedBy() != null ) {
            entity.setCheckedBy( request.getCheckedBy() );
        }
    }
}

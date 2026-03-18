package com.complianceaudit.mapper;

import com.complianceaudit.dto.request.CreateComplianceRecordRequest;
import com.complianceaudit.dto.response.ComplianceRecordResponse;
import com.complianceaudit.entity.ComplianceRecord;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ComplianceRecordMapper {

    ComplianceRecord toEntity(CreateComplianceRecordRequest request);

    ComplianceRecordResponse toResponse(ComplianceRecord entity);

    List<ComplianceRecordResponse> toResponseList(List<ComplianceRecord> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(@MappingTarget ComplianceRecord entity,
                                 com.complianceaudit.dto.request.UpdateComplianceRecordRequest request);
}

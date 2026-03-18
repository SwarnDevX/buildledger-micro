package com.complianceaudit.mapper;

import com.complianceaudit.dto.request.CreateAuditRequest;
import com.complianceaudit.dto.request.UpdateAuditRequest;
import com.complianceaudit.dto.response.AuditResponse;
import com.complianceaudit.entity.Audit;
import com.complianceaudit.enums.AuditStatus;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuditMapper {

    @Mapping(target = "status", expression = "java(com.complianceaudit.enums.AuditStatus.OPEN)")
    Audit toEntity(CreateAuditRequest request);

    AuditResponse toResponse(Audit entity);

    List<AuditResponse> toResponseList(List<Audit> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(@MappingTarget Audit entity, UpdateAuditRequest request);
}

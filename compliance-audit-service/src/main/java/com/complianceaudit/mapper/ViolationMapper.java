package com.complianceaudit.mapper;

import com.complianceaudit.dto.request.CreateViolationRequest;
import com.complianceaudit.dto.response.ViolationResponse;
import com.complianceaudit.entity.Violation;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ViolationMapper {

    @Mapping(target = "status", expression = "java(com.complianceaudit.enums.ViolationStatus.OPEN)")
    Violation toEntity(CreateViolationRequest request);

    ViolationResponse toResponse(Violation entity);

    List<ViolationResponse> toResponseList(List<Violation> entities);
}

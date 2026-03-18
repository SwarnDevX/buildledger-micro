package com.complianceaudit.service;

import com.complianceaudit.dto.request.CreateViolationRequest;
import com.complianceaudit.dto.response.ViolationResponse;

import java.util.List;

public interface ViolationService {
    List<ViolationResponse> getViolations(String status, Long contractId);
    ViolationResponse createViolation(CreateViolationRequest request);
    ViolationResponse resolveViolation(Long id);
}

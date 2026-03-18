package com.complianceaudit.service.serviceimpl;

import com.complianceaudit.dto.request.CreateViolationRequest;
import com.complianceaudit.dto.response.ViolationResponse;
import com.complianceaudit.entity.Violation;
import com.complianceaudit.enums.ViolationStatus;
import com.complianceaudit.exception.BadRequestException;
import com.complianceaudit.exception.ResourceNotFoundException;
import com.complianceaudit.mapper.ViolationMapper;
import com.complianceaudit.repository.ViolationRepository;
import com.complianceaudit.service.ViolationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViolationServiceImpl implements ViolationService {

    private final ViolationRepository violationRepository;
    private final ViolationMapper violationMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ViolationResponse> getViolations(String status, Long contractId) {
        ViolationStatus violationStatus = status != null ? ViolationStatus.valueOf(status.toUpperCase()) : null;
        return violationMapper.toResponseList(violationRepository.findByFilters(violationStatus, contractId));
    }

    @Override
    @Transactional
    public ViolationResponse createViolation(CreateViolationRequest request) {
        log.info("Creating violation for contractId: {}", request.getContractId());
        Violation violation = violationMapper.toEntity(request);
        return violationMapper.toResponse(violationRepository.save(violation));
    }

    @Override
    @Transactional
    public ViolationResponse resolveViolation(Long id) {
        log.info("Resolving violation id: {}", id);
        Violation violation = violationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Violation", "id", id));
        if (violation.getStatus() == ViolationStatus.RESOLVED) {
            throw new BadRequestException("Violation with id " + id + " is already resolved.");
        }
        violation.setStatus(ViolationStatus.RESOLVED);
        violation.setResolvedAt(LocalDateTime.now());
        return violationMapper.toResponse(violationRepository.save(violation));
    }
}

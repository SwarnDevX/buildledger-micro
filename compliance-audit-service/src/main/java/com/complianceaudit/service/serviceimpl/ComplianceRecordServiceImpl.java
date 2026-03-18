package com.complianceaudit.service.serviceimpl;

import com.complianceaudit.dto.request.BulkComplianceRequest;
import com.complianceaudit.dto.request.CreateComplianceRecordRequest;
import com.complianceaudit.dto.request.UpdateComplianceRecordRequest;
import com.complianceaudit.dto.response.BulkComplianceResponse;
import com.complianceaudit.dto.response.ComplianceRecordResponse;
import com.complianceaudit.entity.ComplianceRecord;
import com.complianceaudit.enums.ComplianceResult;
import com.complianceaudit.enums.ComplianceType;
import com.complianceaudit.exception.ResourceNotFoundException;
import com.complianceaudit.mapper.ComplianceRecordMapper;
import com.complianceaudit.repository.ComplianceRecordRepository;
import com.complianceaudit.service.ComplianceRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComplianceRecordServiceImpl implements ComplianceRecordService {

    private final ComplianceRecordRepository repository;
    private final ComplianceRecordMapper mapper;

    @Override
    @Transactional
    public ComplianceRecordResponse createComplianceRecord(CreateComplianceRecordRequest request) {
        log.info("Creating compliance record for contractId: {}", request.getContractId());
        ComplianceRecord entity = mapper.toEntity(request);
        ComplianceRecord saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComplianceRecordResponse> getAllComplianceRecords(
            Long contractId, String type, String result, LocalDate dateFrom, LocalDate dateTo) {

        ComplianceType complianceType = type != null ? ComplianceType.valueOf(type.toUpperCase()) : null;
        ComplianceResult complianceResult = result != null ? ComplianceResult.valueOf(result.toUpperCase()) : null;

        List<ComplianceRecord> records = repository.findByFilters(contractId, complianceType, complianceResult, dateFrom, dateTo);
        return mapper.toResponseList(records);
    }

    @Override
    @Transactional(readOnly = true)
    public ComplianceRecordResponse getComplianceRecordById(Long id) {
        ComplianceRecord record = findByIdOrThrow(id);
        return mapper.toResponse(record);
    }

    @Override
    @Transactional
    public ComplianceRecordResponse updateComplianceRecord(Long id, UpdateComplianceRecordRequest request) {
        log.info("Updating compliance record id: {}", id);
        ComplianceRecord record = findByIdOrThrow(id);
        mapper.updateEntityFromRequest(record, request);
        return mapper.toResponse(repository.save(record));
    }

    @Override
    @Transactional
    public void deleteComplianceRecord(Long id) {
        log.info("Deleting compliance record id: {}", id);
        findByIdOrThrow(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComplianceRecordResponse> getComplianceByContractId(Long contractId) {
        return mapper.toResponseList(repository.findByContractId(contractId));
    }

    @Override
    @Transactional
    public BulkComplianceResponse bulkComplianceCheck(BulkComplianceRequest request) {
        log.info("Processing bulk compliance check: {} records", request.getRecords().size());
        List<ComplianceRecordResponse> created = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        for (CreateComplianceRecordRequest r : request.getRecords()) {
            try {
                created.add(createComplianceRecord(r));
            } catch (Exception e) {
                errors.add("Failed for contractId " + r.getContractId() + ": " + e.getMessage());
            }
        }

        return BulkComplianceResponse.builder()
                .totalRequested(request.getRecords().size())
                .successCount(created.size())
                .failureCount(errors.size())
                .created(created)
                .errors(errors)
                .build();
    }

    private ComplianceRecord findByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ComplianceRecord", "id", id));
    }
}

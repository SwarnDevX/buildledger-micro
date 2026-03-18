package com.complianceaudit.service;

import com.complianceaudit.dto.request.BulkComplianceRequest;
import com.complianceaudit.dto.request.CreateComplianceRecordRequest;
import com.complianceaudit.dto.request.UpdateComplianceRecordRequest;
import com.complianceaudit.dto.response.BulkComplianceResponse;
import com.complianceaudit.dto.response.ComplianceRecordResponse;

import java.time.LocalDate;
import java.util.List;

public interface ComplianceRecordService {

    ComplianceRecordResponse createComplianceRecord(CreateComplianceRecordRequest request);

    List<ComplianceRecordResponse> getAllComplianceRecords(
            Long contractId, String type, String result, LocalDate dateFrom, LocalDate dateTo);

    ComplianceRecordResponse getComplianceRecordById(Long id);

    ComplianceRecordResponse updateComplianceRecord(Long id, UpdateComplianceRecordRequest request);

    void deleteComplianceRecord(Long id);

    List<ComplianceRecordResponse> getComplianceByContractId(Long contractId);

    BulkComplianceResponse bulkComplianceCheck(BulkComplianceRequest request);
}

package com.complianceaudit.controller;

import com.complianceaudit.dto.request.BulkComplianceRequest;
import com.complianceaudit.dto.request.CreateComplianceRecordRequest;
import com.complianceaudit.dto.request.UpdateComplianceRecordRequest;
import com.complianceaudit.dto.response.ApiResponse;
import com.complianceaudit.dto.response.BulkComplianceResponse;
import com.complianceaudit.dto.response.ComplianceRecordResponse;
import com.complianceaudit.service.ComplianceRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/compliance-records")
@RequiredArgsConstructor
public class ComplianceRecordController {

    private final ComplianceRecordService service;

    @PostMapping
    public ResponseEntity<ApiResponse<ComplianceRecordResponse>> createComplianceRecord(
            @Valid @RequestBody CreateComplianceRecordRequest request) {
        ComplianceRecordResponse response = service.createComplianceRecord(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Compliance record created successfully.", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ComplianceRecordResponse>>> getAllComplianceRecords(
            @RequestParam(required = false) Long contractId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String result,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
        List<ComplianceRecordResponse> records = service.getAllComplianceRecords(contractId, type, result, dateFrom, dateTo);
        return ResponseEntity.ok(ApiResponse.success("Compliance records fetched successfully.", records));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ComplianceRecordResponse>> getComplianceRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Compliance record fetched.", service.getComplianceRecordById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ComplianceRecordResponse>> updateComplianceRecord(
            @PathVariable Long id,
            @Valid @RequestBody UpdateComplianceRecordRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Compliance record updated.", service.updateComplianceRecord(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComplianceRecord(@PathVariable Long id) {
        service.deleteComplianceRecord(id);
        return ResponseEntity.ok(ApiResponse.success("Compliance record deleted."));
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<ApiResponse<List<ComplianceRecordResponse>>> getComplianceByContractId(
            @PathVariable Long contractId) {
        return ResponseEntity.ok(ApiResponse.success("Compliance records by contract fetched.",
                service.getComplianceByContractId(contractId)));
    }

    @PostMapping("/bulk-check")
    public ResponseEntity<ApiResponse<BulkComplianceResponse>> bulkComplianceCheck(
            @Valid @RequestBody BulkComplianceRequest request) {
        BulkComplianceResponse response = service.bulkComplianceCheck(request);
        return ResponseEntity.status(HttpStatus.MULTI_STATUS)
                .body(ApiResponse.success("Bulk compliance check completed.", response));
    }
}

package com.complianceaudit.controller;

import com.complianceaudit.dto.response.ApiResponse;
import com.complianceaudit.dto.response.ValidationResponse;
import com.complianceaudit.service.ComplianceValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/compliance/validate")
@RequiredArgsConstructor
public class ComplianceValidationController {

    private final ComplianceValidationService service;

    @PostMapping("/contract/{contractId}")
    public ResponseEntity<ApiResponse<ValidationResponse>> validateContract(@PathVariable Long contractId) {
        ValidationResponse response = service.validateContract(contractId);
        return ResponseEntity.ok(ApiResponse.success("Contract validation completed.", response));
    }

    @PostMapping("/delivery/{deliveryId}")
    public ResponseEntity<ApiResponse<ValidationResponse>> validateDelivery(@PathVariable Long deliveryId) {
        ValidationResponse response = service.validateDelivery(deliveryId);
        return ResponseEntity.ok(ApiResponse.success("Delivery validation completed.", response));
    }

    @PostMapping("/invoice/{invoiceId}")
    public ResponseEntity<ApiResponse<ValidationResponse>> validateInvoice(@PathVariable Long invoiceId) {
        ValidationResponse response = service.validateInvoice(invoiceId);
        return ResponseEntity.ok(ApiResponse.success("Invoice validation completed.", response));
    }
}

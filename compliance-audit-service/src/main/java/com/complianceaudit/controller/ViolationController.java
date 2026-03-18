package com.complianceaudit.controller;

import com.complianceaudit.dto.request.CreateViolationRequest;
import com.complianceaudit.dto.response.ApiResponse;
import com.complianceaudit.dto.response.ViolationResponse;
import com.complianceaudit.service.ViolationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/compliance/violations")
@RequiredArgsConstructor
public class ViolationController {

    private final ViolationService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ViolationResponse>>> getViolations(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long contractId) {
        List<ViolationResponse> violations = service.getViolations(status, contractId);
        return ResponseEntity.ok(ApiResponse.success("Violations fetched successfully.", violations));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ViolationResponse>> createViolation(
            @Valid @RequestBody CreateViolationRequest request) {
        ViolationResponse response = service.createViolation(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Violation created successfully.", response));
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<ApiResponse<ViolationResponse>> resolveViolation(@PathVariable Long id) {
        ViolationResponse response = service.resolveViolation(id);
        return ResponseEntity.ok(ApiResponse.success("Violation resolved successfully.", response));
    }
}

package com.complianceaudit.service;

import com.complianceaudit.dto.response.ValidationResponse;

public interface ComplianceValidationService {
    ValidationResponse validateContract(Long contractId);
    ValidationResponse validateDelivery(Long deliveryId);
    ValidationResponse validateInvoice(Long invoiceId);
}

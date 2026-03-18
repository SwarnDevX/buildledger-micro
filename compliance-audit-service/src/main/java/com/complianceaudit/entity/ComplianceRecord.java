package com.complianceaudit.entity;

import com.complianceaudit.enums.ComplianceResult;
import com.complianceaudit.enums.ComplianceType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_records")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ComplianceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_id", nullable = false)
    private Long contractId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplianceType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplianceResult result;

    @Column(name = "check_date", nullable = false)
    private LocalDate checkDate;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "checked_by")
    private String checkedBy;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

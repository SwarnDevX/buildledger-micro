package com.complianceaudit.entity;

import com.complianceaudit.enums.AuditScope;
import com.complianceaudit.enums.AuditStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "audits")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditScope scope;

    @Column(name = "officer_id", nullable = false)
    private Long officerId;

    @Column(name = "scheduled_date")
    private LocalDate scheduledDate;

    @Column(name = "closed_date")
    private LocalDate closedDate;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "audit", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AuditEvidence> evidences = new ArrayList<>();

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

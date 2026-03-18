package com.complianceaudit.repository;

import com.complianceaudit.entity.Audit;
import com.complianceaudit.enums.AuditScope;
import com.complianceaudit.enums.AuditStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {

    List<Audit> findByOfficerId(Long officerId);

    @Query("""
        SELECT a FROM Audit a
        WHERE (:status IS NULL OR a.status = :status)
          AND (:scope IS NULL OR a.scope = :scope)
          AND (:dateFrom IS NULL OR a.scheduledDate >= :dateFrom)
          AND (:dateTo IS NULL OR a.scheduledDate <= :dateTo)
    """)
    List<Audit> findByFilters(
            @Param("status") AuditStatus status,
            @Param("scope") AuditScope scope,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo
    );

    List<Audit> findByScope(AuditScope scope);

    long countByStatus(AuditStatus status);
}

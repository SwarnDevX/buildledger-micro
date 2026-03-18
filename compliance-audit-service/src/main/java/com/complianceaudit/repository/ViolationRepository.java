package com.complianceaudit.repository;

import com.complianceaudit.entity.Violation;
import com.complianceaudit.enums.ViolationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolationRepository extends JpaRepository<Violation, Long> {

    @Query("""
        SELECT v FROM Violation v
        WHERE (:status IS NULL OR v.status = :status)
          AND (:contractId IS NULL OR v.contractId = :contractId)
    """)
    List<Violation> findByFilters(
            @Param("status") ViolationStatus status,
            @Param("contractId") Long contractId
    );

    long countByContractId(Long contractId);
    long countByStatus(ViolationStatus status);
}

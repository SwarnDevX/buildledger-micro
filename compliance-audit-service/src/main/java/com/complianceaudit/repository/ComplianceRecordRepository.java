package com.complianceaudit.repository;

import com.complianceaudit.entity.ComplianceRecord;
import com.complianceaudit.enums.ComplianceResult;
import com.complianceaudit.enums.ComplianceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ComplianceRecordRepository extends JpaRepository<ComplianceRecord, Long> {

    List<ComplianceRecord> findByContractId(Long contractId);

    @Query("""
        SELECT c FROM ComplianceRecord c
        WHERE (:contractId IS NULL OR c.contractId = :contractId)
          AND (:type IS NULL OR c.type = :type)
          AND (:result IS NULL OR c.result = :result)
          AND (:dateFrom IS NULL OR c.checkDate >= :dateFrom)
          AND (:dateTo IS NULL OR c.checkDate <= :dateTo)
    """)
    List<ComplianceRecord> findByFilters(
            @Param("contractId") Long contractId,
            @Param("type") ComplianceType type,
            @Param("result") ComplianceResult result,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo
    );

    long countByContractIdAndResult(Long contractId, ComplianceResult result);

    @Query("SELECT COUNT(c) FROM ComplianceRecord c WHERE c.checkDate BETWEEN :from AND :to")
    long countByDateRange(@Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query("SELECT COUNT(c) FROM ComplianceRecord c WHERE c.result = :result AND c.checkDate BETWEEN :from AND :to")
    long countByResultAndDateRange(@Param("result") ComplianceResult result,
                                   @Param("from") LocalDate from,
                                   @Param("to") LocalDate to);
}

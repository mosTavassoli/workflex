package com.workflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.workflex.domain.models.Workation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkationRepository extends JpaRepository<Workation, Long> {

    @Query("""
            SELECT w FROM Workation w
            WHERE w.deletedAt IS NULL
               AND (:employee IS NULL OR LOWER(w.employee) LIKE LOWER(CONCAT('%', :employee, '%')))
               AND (:originCountry IS NULL OR w.originCountry = :originCountry)
            """)
    List<Workation> search(
            @Param("employee") String employee,
            @Param("originCountry") String originCountry
    );


    Optional<Workation> findByIdAndDeletedAtIsNull(Long id);

    List<Workation> findAllByDeletedAtIsNull();
}

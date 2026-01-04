package com.workflex.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.workflex.domain.models.Workation;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WorkationRepository extends JpaRepository<Workation, Long>,
        JpaSpecificationExecutor<Workation> {

    @Query("""
            SELECT w FROM Workation w
            WHERE w.deletedAt IS NULL
               AND (:employee IS NULL OR LOWER(w.employee) LIKE LOWER(CONCAT('%', :employee, '%')))
               AND (:originCountry IS NULL OR LOWER(w.originCountry) = LOWER(:originCountry))
            """)
    Page<Workation> search(
            @Param("employee") String employee,
            @Param("originCountry") String originCountry,
            Pageable pageable
    );


    Optional<Workation> findByIdAndDeletedAtIsNull(Long id);

    Page<Workation> findAllByDeletedAtIsNull(Pageable pageable);
}

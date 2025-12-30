package com.workflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.workflex.domain.models.Workation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkationRepository extends JpaRepository<Workation, Long> {


    @Query("""
                SELECT w FROM Workation w
                WHERE (:employee IS NULL\s
                       OR LOWER(w.employee) LIKE LOWER(CONCAT('%', :employee, '%')))
                  AND (:originCountry IS NULL\s
                       OR w.originCountry = :originCountry)
           \s""")
    List<Workation> search(
            @Param("employee") String employee,
            @Param("originCountry") String originCountry
    );

}

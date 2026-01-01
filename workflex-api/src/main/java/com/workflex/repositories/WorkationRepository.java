package com.workflex.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.workflex.domain.models.Workation;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface WorkationRepository extends JpaRepository<Workation, Long>,
        JpaSpecificationExecutor<Workation> {

    Optional<Workation> findByIdAndDeletedAtIsNull(Long id);

    Page<Workation> findAllByDeletedAtIsNull(Pageable pageable);
}

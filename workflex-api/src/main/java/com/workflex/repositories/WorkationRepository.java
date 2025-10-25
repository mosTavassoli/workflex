package com.workflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.workflex.domain.models.Workation;

public interface WorkationRepository extends JpaRepository<Workation, Long> {
}

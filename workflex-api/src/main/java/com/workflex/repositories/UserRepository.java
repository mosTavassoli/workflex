package com.workflex.repositories;

import com.workflex.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {}

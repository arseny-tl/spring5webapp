package com.tl.spring5webapp.repository;

import com.tl.spring5webapp.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {}

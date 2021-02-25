package com.tsipadan.repository;

import com.tsipadan.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for UserRole
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}

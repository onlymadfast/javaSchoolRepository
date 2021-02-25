package com.tsipadan.repository;

import com.tsipadan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String name);

  User findUserByUsername(String name);

  User findById(long id);

}

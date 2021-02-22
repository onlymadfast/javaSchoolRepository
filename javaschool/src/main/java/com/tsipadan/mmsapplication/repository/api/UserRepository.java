package com.tsipadan.mmsapplication.repository.api;

import com.tsipadan.mmsapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String name);

  User findById(long id);


}

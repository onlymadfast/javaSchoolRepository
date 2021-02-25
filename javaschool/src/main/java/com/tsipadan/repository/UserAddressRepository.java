package com.tsipadan.repository;

import com.tsipadan.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository for UserAddress
 */
@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

  UserAddress findUserAddressByUser_Username(String username);

  UserAddress findById(long id);

  @Query(value = " select max(id) from UserAddress")
  Long findMaxId();

}

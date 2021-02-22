package com.tsipadan.mmsapplication.repository.api;

import com.tsipadan.mmsapplication.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

  UserAddress findUserAddressByUser_Username(String username);

  UserAddress findById(long id);

}

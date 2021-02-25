package com.tsipadan.repository;

import com.tsipadan.entity.UserOrder;
import com.tsipadan.enumaration.StatusPay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for UserOrder
 */
@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {

  UserOrder findById(long id);

  UserOrder findByOrderNum(String id);

  List<UserOrder> findByUser_Username(String name);

  Page<UserOrder> findAll(Pageable pageable);

}

package com.tsipadan.repository;

import com.tsipadan.enumaration.StatusPay;
import com.tsipadan.entity.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for UserOrder
 */
@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {

  UserOrder findById(long id);

  UserOrder findByOrderNum(String id);

  Page<UserOrder> findAll(Pageable pageable);

  UserOrder findUserOrderByStatusPayIsLike(StatusPay statusPay);

}

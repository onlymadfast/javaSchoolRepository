package com.tsipadan.mmsapplication.service;

import com.tsipadan.mmsapplication.dao.OrderDAO;
import com.tsipadan.mmsapplication.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

  private final OrderDAO orderDAO;

  @Override
  public List<Order> search(String keyword) {
    return orderDAO.search(keyword);
  }

}

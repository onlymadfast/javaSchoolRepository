package com.tsipadan.mmsapplication.service;

import com.tsipadan.mmsapplication.entity.Order;

import java.util.List;

public interface OrderService {

  List<Order> search(String keyword);
}

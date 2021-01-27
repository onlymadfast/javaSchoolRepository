package com.tsipadan.mmsapplication.dao;

import com.tsipadan.mmsapplication.entity.Account;
import com.tsipadan.mmsapplication.entity.Order;
import com.tsipadan.mmsapplication.model.*;

import java.util.List;

public interface OrderDAO {

  void saveOrder(CartInfo cartInfo);

  PaginationResult<OrderInfo>listOrderInfo(int page, int maxResult, int maxNavigationPage);

  OrderInfo getOrderInfo(String orderId);

  OrderInfo getSpecificOrderInfo(String orderNum);

  List<OrderDetailInfo>listOrderDetailInfos(String orderId);

  List<Order> search(String keyword);

}

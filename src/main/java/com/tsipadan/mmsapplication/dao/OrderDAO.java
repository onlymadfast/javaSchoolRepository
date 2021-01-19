package com.tsipadan.mmsapplication.dao;

import com.tsipadan.mmsapplication.model.CartInfo;
import com.tsipadan.mmsapplication.model.OrderDetailInfo;
import com.tsipadan.mmsapplication.model.OrderInfo;
import com.tsipadan.mmsapplication.model.PaginationResult;

import java.util.List;

public interface OrderDAO {

  void saveOrder(CartInfo cartInfo);

  PaginationResult<OrderInfo>listOrderInfo(int page, int maxResult, int maxNavigationPage);

  OrderInfo getOrderInfo(String orderId);

  List<OrderDetailInfo>listOrderDetailInfos(String orderId);

}

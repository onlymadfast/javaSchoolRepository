package com.tsipadan.service.api;

import com.tsipadan.dto.UserOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

  void saveOrder(UserOrderDTO userOrderDTO, UserDetails userDetails, String howPay, String howDeliver, HttpServletRequest request);

  UserOrderDTO findById(long id);

  UserOrderDTO getOrderInformation(String orderNum);

  Page<UserOrderDTO> getAllPages(int pageNumber);

  List<UserOrderDTO> getAllResults();

  List<UserOrderDTO> getUserOrderInfoByName(String name);

}

package com.tsipadan.mmsapplication.service.api;

import com.tsipadan.dto.UserOrderDTO;
import org.springframework.data.domain.Page;

public interface OrderService {

  void saveOrder(UserOrderDTO userOrderDTO);

  UserOrderDTO findById(long id);

  UserOrderDTO getOrderInformation(String orderNum);

  Page<UserOrderDTO> getAllPages(int pageNumber);

}

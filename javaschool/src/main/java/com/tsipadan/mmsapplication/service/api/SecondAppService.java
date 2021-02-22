package com.tsipadan.mmsapplication.service.api;

import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.dto.UserDTO;
import com.tsipadan.dto.UserOrderDTO;

import java.util.List;

public interface SecondAppService {

  List<UserOrderDTO> findAllInfoAboutOrder();

  List<GoodsDTO> findAllGoods();

  List<UserDTO> findAllUsers();

}

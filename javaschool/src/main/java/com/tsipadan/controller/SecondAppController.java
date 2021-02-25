package com.tsipadan.controller;

import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.dto.UserDTO;
import com.tsipadan.dto.UserOrderDTO;
import com.tsipadan.service.api.SecondAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SecondAppController {

  private final SecondAppService secondAppService;

  /**
   * Return all orders
   *
   * @return List<UserOrderDTO>
   * **/
  @GetMapping(value = "secondapp/orders", produces = "application/json;charset=utf8")
  public List<UserOrderDTO> getAllOrder(){
    return secondAppService.findAllInfoAboutOrder();
  }

  /**
   * Return all goods
   *
   * @return List<GoodsDTO>
   * **/
  @GetMapping(value = "secondapp/goods", produces = "application/json;charset=utf8")
  public List<GoodsDTO> getAllGoods(){
    return secondAppService.findAllGoods();
  }

  /**
   * Return all users
   *
   * @return List<UserDTO>
   * **/
  @GetMapping(value = "secondapp/users", produces = "application/json;charset=utf8")
  public List<UserDTO> getAllUsers(){
    return secondAppService.findAllUsers();
  }

}

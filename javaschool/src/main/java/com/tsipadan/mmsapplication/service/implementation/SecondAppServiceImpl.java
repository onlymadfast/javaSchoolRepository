package com.tsipadan.mmsapplication.service.implementation;

import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.dto.UserDTO;
import com.tsipadan.dto.UserOrderDTO;
import com.tsipadan.mmsapplication.entity.Goods;
import com.tsipadan.mmsapplication.entity.User;
import com.tsipadan.mmsapplication.entity.UserOrder;
import com.tsipadan.mmsapplication.mapper.ProductMapper;
import com.tsipadan.mmsapplication.mapper.UserMapper;
import com.tsipadan.mmsapplication.mapper.UserOrderMapper;
import com.tsipadan.mmsapplication.repository.api.OrderRepository;
import com.tsipadan.mmsapplication.repository.api.ProductRepository;
import com.tsipadan.mmsapplication.repository.api.UserRepository;
import com.tsipadan.mmsapplication.service.api.SecondAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SecondAppServiceImpl implements SecondAppService {

  private final OrderRepository orderRepository;
  private final UserOrderMapper orderMapper;
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public List<UserOrderDTO> findAllInfoAboutOrder() {
    final List<UserOrder> userOrderList = orderRepository.findAll();
    return userOrderList.stream()
        .map(orderMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<GoodsDTO> findAllGoods() {
    final List<Goods> goodsList = productRepository.findAll();
    return goodsList.stream()
        .map(productMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<UserDTO> findAllUsers() {
    final List<User> userList = userRepository.findAll();
    return userList.stream()
        .map(userMapper::toDto)
        .collect(Collectors.toList());
  }
}

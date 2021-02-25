package com.tsipadan.service.implementation;

import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.dto.UserDTO;
import com.tsipadan.dto.UserOrderDTO;
import com.tsipadan.entity.Goods;
import com.tsipadan.entity.User;
import com.tsipadan.entity.UserOrder;
import com.tsipadan.mapper.ProductMapper;
import com.tsipadan.mapper.UserMapper;
import com.tsipadan.mapper.UserOrderMapper;
import com.tsipadan.repository.OrderRepository;
import com.tsipadan.repository.ProductRepository;
import com.tsipadan.repository.UserRepository;
import com.tsipadan.service.api.SecondAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecondAppServiceImpl implements SecondAppService {

  private final OrderRepository orderRepository;
  private final UserOrderMapper orderMapper;
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  /**
   * Get all orders
   *
   * @return List<UserOrderDTO>
   */
  @Override
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<UserOrderDTO> findAllInfoAboutOrder() {
    final List<UserOrder> userOrderList = orderRepository.findAll();
    return userOrderList.stream()
        .map(orderMapper::toDto)
        .collect(Collectors.toList());
  }

  /**
   * Get all goods
   *
   * @return List<GoodsDTO>
   */
  @Override
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<GoodsDTO> findAllGoods() {
    final List<Goods> goodsList = productRepository.findAll();
    return goodsList.stream()
        .map(productMapper::toDto)
        .collect(Collectors.toList());
  }

  /**
   * Get all users
   *
   * @return List<UserDTO>
   */
  @Override
  @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
  public List<UserDTO> findAllUsers() {
    final List<User> userList = userRepository.findAll();
    return userList.stream()
        .map(userMapper::toDto)
        .collect(Collectors.toList());
  }
}

package com.tsipadan.service.implementation;

import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.dto.UserOrderDTO;
import com.tsipadan.entity.Goods;
import com.tsipadan.entity.User;
import com.tsipadan.entity.UserAddress;
import com.tsipadan.entity.UserOrder;
import com.tsipadan.enumaration.HowDeliver;
import com.tsipadan.enumaration.HowPay;
import com.tsipadan.enumaration.StatusOrder;
import com.tsipadan.enumaration.StatusPay;
import com.tsipadan.mapper.ItemCategoryMapper;
import com.tsipadan.mapper.ProductMapper;
import com.tsipadan.mapper.UserAddressMapper;
import com.tsipadan.mapper.UserOrderMapper;
import com.tsipadan.repository.OrderRepository;
import com.tsipadan.repository.ProductRepository;
import com.tsipadan.repository.UserAddressRepository;
import com.tsipadan.repository.UserRepository;
import com.tsipadan.service.api.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final UserOrderMapper userOrderMapper;
  private final UserAddressMapper addressMapper;
  private final ProductMapper productMapper;
  private final ItemCategoryMapper itemCategoryMapper;
  private final UserRepository userRepository;
  private final UserAddressRepository addressRepository;
  private final ProductRepository productRepository;

  /**
   * Save order in Db
   *
   * @param userOrderDTO - user order
   * @param userDetails  - userDetails
   * @param howPay       - howPay
   * @param howDeliver   howDeliver
   * @param request      - get some info from session cart
   */
  @SneakyThrows
  @Override
  @Transactional
  public void saveOrder(UserOrderDTO userOrderDTO, UserDetails userDetails, String howPay, String howDeliver,
                        HttpServletRequest request) {

    User user = userRepository.findUserByUsername(userDetails.getUsername());
    UserOrder userOrder = userOrderMapper.toEntity(userOrderDTO);
    UserAddress userAddress = addressMapper.toEntity(userOrderDTO.getUserAddressDTO());
    userAddress.setId(addressRepository.findMaxId() + 1);
    userAddress.setUserCountry(userOrderDTO.getUserAddressDTO().getUserCountry());
    userAddress.setUserCity(userOrderDTO.getUserAddressDTO().getUserCity());
    userAddress.setUserZip(userOrderDTO.getUserAddressDTO().getUserZip());
    userAddress.setUserStreet(userOrderDTO.getUserAddressDTO().getUserStreet());
    userAddress.setUserHouse(userOrderDTO.getUserAddressDTO().getUserHouse());
    userAddress.setUserApartment(userOrderDTO.getUserAddressDTO().getUserApartment());
    userAddress.setUser(user);
    addressRepository.save(userAddress);

    userOrder.setOrderNum(UUID.randomUUID().toString());
    userOrder.setOrderDate(new Date());
    userOrder.setHowPay(HowPay.valueOf(howPay));
    userOrder.setHowDeliver(HowDeliver.valueOf(howDeliver));
    userOrder.setStatusPay(StatusPay.PAID);
    userOrder.setStatusOrder(StatusOrder.ON_THE_WAY);
    userOrder.setAmount(userOrderDTO.getAmountTotal());

    List<GoodsDTO> goodsDTOList = userOrderDTO.getGoodsDTOList();
    for (GoodsDTO dto : goodsDTOList) {
      Goods goods = productMapper.toEntity(dto);
      goods.setItemName(dto.getItemName());
      goods.setItemPrice(dto.getItemPrice());
      goods.setItemQuantity(dto.getItemQuantity());
      final byte[] bytes = dto.getImage() == null ? null : dto.getImage().getBytes();
      goods.setImage(bytes);
      goods.setItemSize(dto.getItemSize());
      goods.setUserOrder(userOrder);
      goods.setItemCategory(itemCategoryMapper.toEntity(dto.getCategory()));
      productRepository.save(goods);
    }
    request.getSession().setAttribute("lastOrderedCart", userOrderMapper.toDto(userOrder));
    userOrder.setUser(user);

    userOrder.setUserAddress(userAddress);
    orderRepository.save(userOrder);

    log.info("Order with number: " + userOrder.getOrderNum() + " saved in Db");
  }

  /**
   * Find userOrder
   *
   * @param id - specific order id
   * @return specific userOrder
   */
  @Override
  @Transactional(readOnly = true)
  public UserOrderDTO findById(long id) {
    return userOrderMapper.toDto(orderRepository.findById(id));
  }

  /**
   * Get all orders
   *
   * @param pageNumber - page number
   * @return Page<UserOrderDTO>
   */
  @Override
  @Transactional
  public Page<UserOrderDTO> getAllPages(int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber - 1, 3);
    return orderRepository.findAll(pageable).map(userOrderMapper::toDto);
  }

  @Override
  @Transactional
  public List<UserOrderDTO> getAllResults() {
    return orderRepository.findAll().stream().map(userOrderMapper::toDto).collect(Collectors.toList());
  }

  @Override
  @Transactional
  public List<UserOrderDTO> getUserOrderInfoByName(String name) {
    List<UserOrder> orders = orderRepository.findByUser_Username(name);
    List<UserOrderDTO> dto = new ArrayList<>();
    for (UserOrder order : orders) {
      dto.add(userOrderMapper.toDto(order));
    }
    return dto;
  }


}

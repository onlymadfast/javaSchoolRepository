package com.tsipadan.mapper;

import com.tsipadan.dto.UserAddressDTO;
import com.tsipadan.dto.UserDTO;
import com.tsipadan.dto.UserOrderDTO;
import com.tsipadan.entity.User;
import com.tsipadan.entity.UserAddress;
import com.tsipadan.entity.UserOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Convert UserOrder -> UserOrderDTO and vice-versa
 */
@Component
@RequiredArgsConstructor
public class UserOrderMapper {

  private final ModelMapper mapper;

  public UserOrder toEntity(UserOrderDTO dto) {
    final UserOrder userOrder = new UserOrder();
    userOrder.setId(dto.getId());
    userOrder.setOrderNum(dto.getOrderNum());
    userOrder.setOrderDate(dto.getOrderDate());
    userOrder.setHowPay(dto.getHowPay());
    userOrder.setHowDeliver(dto.getHowDeliver());
    userOrder.setStatusPay(dto.getStatusPay());
    userOrder.setStatusOrder(dto.getStatusOrder());
    userOrder.setAmount(dto.getAmount());
    userOrder.setUserAddress(mapper.map(dto.getUserAddressDTO(), UserAddress.class));
    userOrder.setUser(mapper.map(dto.getUserDto(), User.class));
    return userOrder;
  }

  public UserOrderDTO toDto(UserOrder entity) {
    final UserOrderDTO dto = new UserOrderDTO();
    dto.setId(entity.getId());
    dto.setOrderNum(entity.getOrderNum());
    dto.setOrderDate(entity.getOrderDate());
    dto.setHowPay(entity.getHowPay());
    dto.setHowDeliver(entity.getHowDeliver());
    dto.setStatusPay(entity.getStatusPay());
    dto.setStatusOrder(entity.getStatusOrder());
    dto.setAmount(entity.getAmount());
    dto.setUserAddressDTO(mapper.map(entity.getUserAddress(), UserAddressDTO.class));
    dto.setUserDto(mapper.map(entity.getUser(), UserDTO.class));
    return dto;
  }

}
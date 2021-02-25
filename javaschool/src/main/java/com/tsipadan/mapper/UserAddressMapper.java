package com.tsipadan.mapper;

import com.tsipadan.dto.UserAddressDTO;
import com.tsipadan.entity.UserAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Convert UserAddress -> UserAddressDTO and vice-versa
 */
@Component
@RequiredArgsConstructor
public class UserAddressMapper {

  public UserAddress toEntity(UserAddressDTO dto) {
    final UserAddress address = new UserAddress();
    address.setId(dto.getId());
    address.setUserCountry(dto.getUserCountry());
    address.setUserCity(dto.getUserCity());
    address.setUserZip(dto.getUserZip());
    address.setUserStreet(dto.getUserStreet());
    address.setUserHouse(dto.getUserHouse());
    address.setUserApartment(dto.getUserApartment());
//    address.setOrder(mapper.map(dto.getUserOrderDTO(), UserOrder.class));
    return address;
  }

  public UserAddressDTO toDto(UserAddress entity) {
    final UserAddressDTO dto = new UserAddressDTO();
    dto.setId(entity.getId());
    dto.setUserCountry(entity.getUserCountry());
    dto.setUserCity(entity.getUserCity());
    dto.setUserZip(entity.getUserZip());
    dto.setUserStreet(entity.getUserStreet());
    dto.setUserApartment(entity.getUserApartment());
//    dto.setUserOrderDTO(mapper.map(entity.getOrder(), UserOrderDTO.class));
    return dto;
  }

}

package com.tsipadan.mapper;

import com.tsipadan.dto.UserOrderDTO;
import com.tsipadan.entity.UserOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Convert UserOrder -> UserOrderDTO and vice-versa
 */
@Component
@RequiredArgsConstructor
public class UserOrderMapper {

  private final ModelMapper modelMapper;

  public UserOrder toEntity(UserOrderDTO dto) {
    return Objects.isNull(dto) ? null : modelMapper.map(dto, UserOrder.class);
  }

  public UserOrderDTO toDto(UserOrder entity) {
    return Objects.isNull(entity) ? null : modelMapper.map(entity, UserOrderDTO.class);
  }

}
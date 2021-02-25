package com.tsipadan.mapper;

import com.tsipadan.dto.UserAddressDTO;
import com.tsipadan.entity.UserAddress;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Convert UserAddress -> UserAddressDTO and vice-versa
 */
@Component
@RequiredArgsConstructor
public class UserAddressMapper {

  private final ModelMapper modelMapper;

  public UserAddress toEntity(UserAddressDTO dto) {
    return Objects.isNull(dto) ? null : modelMapper.map(dto, UserAddress.class);
  }

  public UserAddressDTO toDto(UserAddress entity) {
    return Objects.isNull(entity) ? null : modelMapper.map(entity, UserAddressDTO.class);
  }

}

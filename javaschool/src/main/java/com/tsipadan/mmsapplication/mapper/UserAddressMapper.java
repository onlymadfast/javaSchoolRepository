package com.tsipadan.mmsapplication.mapper;

import com.tsipadan.dto.UserAddressDTO;
import com.tsipadan.mmsapplication.entity.UserAddress;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

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

package com.tsipadan.mmsapplication.mapper;

import com.tsipadan.dto.UserDTO;
import com.tsipadan.mmsapplication.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final ModelMapper modelMapper;

  public User toEntity(UserDTO dto) {
    return Objects.isNull(dto) ? null : modelMapper.map(dto, User.class);
  }

  public UserDTO toDto(User entity) {
    return Objects.isNull(entity) ? null : modelMapper.map(entity, UserDTO.class);
  }

}

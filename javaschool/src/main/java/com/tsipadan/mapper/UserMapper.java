package com.tsipadan.mapper;

import com.tsipadan.dto.UserAddressDTO;
import com.tsipadan.dto.UserDTO;
import com.tsipadan.entity.User;
import com.tsipadan.entity.UserAddress;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Convert User -> UserDTO and vice-versa
 */
@Component
@RequiredArgsConstructor
public class UserMapper {

  public User toEntity(UserDTO dto) {
    final User user = new User();
    user.setId(dto.getId());
    user.setUsername(dto.getUsername());
    user.setPassword(dto.getPassword());
    user.setUserFirstName(dto.getUserFirstName());
    user.setUserLastName(dto.getUserLastName());
    user.setUserBirthday(dto.getUserBirthday());
    user.setUserEmail(dto.getUserEmail());
    return user;
  }

  public UserDTO toDto(User entity) {
    final UserDTO dto = new UserDTO();
    dto.setId(entity.getId());
    dto.setUsername(entity.getUsername());
    dto.setPassword(entity.getPassword());
    dto.setUserFirstName(entity.getUserFirstName());
    dto.setUserLastName(entity.getUserLastName());
    dto.setUserBirthday(entity.getUserBirthday());
    dto.setUserEmail(entity.getUserEmail());
    return dto;
  }

}

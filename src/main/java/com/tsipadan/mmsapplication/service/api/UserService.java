package com.tsipadan.mmsapplication.service.api;

import com.tsipadan.mmsapplication.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

  UserDTO findByUserName(String username);

  UserDTO findById(long id);

  boolean save(UserDTO userDTO);

  void update(UserDTO userDTO);

  void updatePassword(String newPassword, String userName);


}

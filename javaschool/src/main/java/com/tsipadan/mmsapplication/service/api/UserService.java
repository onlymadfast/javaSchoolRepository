package com.tsipadan.mmsapplication.service.api;

import com.tsipadan.dto.UserAddressDTO;
import com.tsipadan.dto.UserDTO;

public interface UserService {

  UserDTO findByUserName(String username);

  UserDTO findById(long id);

  boolean save(UserDTO userDTO);

  void update(UserDTO userDTO);

  void updatePassword(String newPassword, String userName);

  UserAddressDTO findAddressByUsername(String username);

  void updateAddress(UserAddressDTO userAddressDTO);

}

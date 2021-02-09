package com.tsipadan.mmsapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private long id;
  private String userName;
  private String userPassword;
  private String userFirstName;
  private String userLastName;
  private Date userBirthday;
  private String userEmail;
  private Set<UserRoleDTO> userRoleDto;
  private UserAddressDTO userAddressDto;

}

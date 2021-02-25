package com.tsipadan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private Long id;

  private String username;
  private String password;

  private String userFirstName;
  private String userLastName;
  private Date userBirthday;
  private String userEmail;

}

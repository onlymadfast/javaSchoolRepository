package com.tsipadan.dto;

import lombok.*;

import javax.validation.constraints.Min;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private Long id;

  private String username;
  @Min(value = 3, message = "min password values = 3")
  private String password;

  private String userFirstName;
  private String userLastName;
  private Date userBirthday;
  private String userEmail;

}

package com.tsipadan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressDTO {

  private long id;
  @NotBlank
  private String userCountry;
  @NotBlank
  private String userCity;
  @NotBlank
  private String userZip;
  @NotBlank
  private String userStreet;
  @NotBlank
  private int userHouse;
  @NotBlank
  private int userApartment;

  private boolean valid;

  private UserOrderDTO userOrderDTO;

  private UserDTO userDTO;


}

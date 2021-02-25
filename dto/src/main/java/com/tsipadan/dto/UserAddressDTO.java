package com.tsipadan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressDTO {

  private Long id;
  @NotBlank
  private String userCountry;
  @NotBlank
  private String userCity;
  @NotBlank
  private String userZip;
  @NotBlank
  private String userStreet;

  private Integer userHouse;

  private Integer userApartment;

  private boolean valid;

}

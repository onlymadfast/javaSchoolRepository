package com.tsipadan.mmsapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressDTO {

  private long id;
  private String userCountry;
  private String userCity;
  private String userZip;
  private String userStreet;
  private String userHouse;
  private String userApartment;

}

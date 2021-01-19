package com.tsipadan.mmsapplication.model;

import lombok.Data;

@Data
public class CustomerInfo {

  private String firstName;
  private String lastName;
  private String birthday;
  private String email;
  private String country;
  private String city;
  private String zip;
  private String street;
  private String house;
  private String apartment;

  private boolean valid;

}

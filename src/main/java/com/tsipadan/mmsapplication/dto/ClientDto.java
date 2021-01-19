//package com.tsipadan.mmsapplication.dto;
//
//import com.tsipadan.mmsapplication.model.Client;
//import lombok.Data;
//
//@Data
//public class ClientDto {
//
//  private int id;
//  private String firstName;
//  private String lastName;
//  private String birthday;
//  private String email;
//  private String password;
//  private String country;
//  private String city;
//  private String zip;
//  private String street;
//  private String house;
//  private String apartment;
//
//  public Client ConvertClientToEntity() {
//    Client client = new Client();
//    client.setId(this.getId());
//    client.setFirstName(this.getFirstName());
//    client.setLastName(this.getLastName());
//    client.setEmail(this.getEmail());
//    client.setPassword(this.getPassword());
//    client.setCountry(this.getCountry());
//    client.setCity(this.getCity());
//    client.setZip(this.getZip());
//    client.setStreet(this.getStreet());
//    client.setHouse(this.getHouse());
//    client.setApartment(this.getApartment());
//    return client;
//  }
//
//}

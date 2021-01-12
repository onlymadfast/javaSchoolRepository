package com.tsipadan.mmsapplication.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Data
@Entity
@Table(name = "client_table")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "birthday")
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(style = "F-")
  private java.util.Date birthday;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "country")
  private String country;

  @Column(name = "city")
  private String city;

  @Column(name = "zip")
  private String zip;

  @Column(name = "street")
  private String street;

  @Column(name = "house")
  private String house;

  @Column(name = "apartment")
  private String apartment;


}



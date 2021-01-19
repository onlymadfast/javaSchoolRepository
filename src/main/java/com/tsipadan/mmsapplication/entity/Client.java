//package com.tsipadan.mmsapplication.model;
//
//import lombok.Data;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@Table(name = "client_table")
//public class Client {
//
//  public static final String ROLE_MANAGER = "MANAGER";
//  public static final String ROLE_EMPLOYEE = "EMPLOYEE";
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column(name = "id", nullable = false)
//  private int id;
//
//  @Column(name = "firstName", nullable = false)
//  private String firstName;
//
//  @Column(name = "lastName", nullable = false)
//  private String lastName;
//
//  @Column(name = "birthday", nullable = false)
//  @Temporal(TemporalType.DATE)
//  @DateTimeFormat(style = "F-")
//  private java.util.Date birthday;
//
//  @Column(name = "email", nullable = false)
//  private String email;
//
//  @Column(name = "password", nullable = false)
//  private String password;
//
//  @Column(name = "client_role", nullable = false)
//  private String client_role;
//
//  @Column(name = "country", nullable = false)
//  private String country;
//
//  @Column(name = "city", nullable = false)
//  private String city;
//
//  @Column(name = "zip", nullable = false)
//  private String zip;
//
//  @Column(name = "street", nullable = false)
//  private String street;
//
//  @Column(name = "house", nullable = false)
//  private String house;
//
//  @Column(name = "apartment", nullable = false)
//  private String apartment;
//
//  @Column(name = "Active", length = 1, nullable = false)
//  private boolean active;
//
//
//}


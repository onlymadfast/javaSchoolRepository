//package com.tsipadan.mmsapplication.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Data
//@Entity
//@Table(name = "orders", uniqueConstraints = {@UniqueConstraint(columnNames = "Order_Num")})
//public class Order {
//
//  @Id
//  @Column(name = "ID", length = 50)
//  private String id;
//
//  @Column(name = "Amount",nullable = false)
//  private double amount;
//
//  @Column(name = "Order_Date",nullable = false)
//  private Date orderDate;
//
//  @Column(name = "Order_Num",nullable = false)
//  private int orderNum;
//
//  @Column(name = "Customer_FirstName", nullable = false)
//  private String customerFirstName;
//
//  @Column(name = "Customer_LastName", nullable = false)
//  private String customerLastName;
//
//  @Column(name = "Customer_Birthday", nullable = false)
//  private String customerBirthday;
//
//  @Column(name = "Customer_Email", nullable = false)
//  private String customerEmail;
//
//  @Column(name = "Customer_Country", nullable = false)
//  private String customerCountry;
//
//  @Column(name = "Customer_City", nullable = false)
//  private String customerCity;
//
//  @Column(name = "Customer_Zip", nullable = false)
//  private String customerZip;
//
//  @Column(name = "Customer_Street", nullable = false)
//  private String customerStreet;
//
//  @Column(name = "Customer_House", nullable = false)
//  private String customerHouse;
//
//  @Column(name = "Customer_Apartment", nullable = false)
//  private String customerApartment;
//
//}

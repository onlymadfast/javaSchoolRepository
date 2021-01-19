package com.tsipadan.mmsapplication.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders", uniqueConstraints = {@UniqueConstraint(columnNames = "Order_Num")})
public class Order {

  @Id
  @Column(name = "ID", length = 50)
  private String ID;

  @Column(name = "Amount",nullable = false)
  private double amount;

  @Column(name = "Order_Date",nullable = false)
  private Date orderDate;

  @Column(name = "Order_Num",nullable = false)
  private int orderNum;

  @Column(name = "Customer_FirstName", nullable = false)
  private String firstName;

  @Column(name = "Customer_LastName", nullable = false)
  private String lastName;

  @Column(name = "Customer_Birthday", nullable = false)
  private String birthday;

  @Column(name = "Customer_Email", nullable = false)
  private String email;

  @Column(name = "Customer_Country", nullable = false)
  private String country;

  @Column(name = "Customer_City", nullable = false)
  private String city;

  @Column(name = "Customer_Zip", nullable = false)
  private String zip;

  @Column(name = "Customer_Street", nullable = false)
  private String street;

  @Column(name = "Customer_House", nullable = false)
  private String house;

  @Column(name = "Customer_Apartment", nullable = false)
  private String apartment;

}

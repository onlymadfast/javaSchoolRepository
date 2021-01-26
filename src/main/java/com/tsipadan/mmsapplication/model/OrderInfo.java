package com.tsipadan.mmsapplication.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderInfo {

  private String id;
  private int orderNum;
  private Date orderDate;
  private double amount;

  private String customerFirstName;
  private String customerEmail;

  private String customerCountry;
  private String customerCity;
  private String customerZip;
  private String customerStreet;
  private String customerHouse;
  private String customerApartment;

  private List<OrderDetailInfo> details;

  public OrderInfo(String id, int orderNum, Date orderDate,
                   double amount, String customerFirstName,
                   String customerEmail, String customerCountry,
                   String customerCity, String customerZip,
                   String customerStreet, String customerHouse,
                   String customerApartment) {
    this.id = id;
    this.orderNum = orderNum;
    this.orderDate = orderDate;
    this.amount = amount;
    this.customerFirstName = customerFirstName;
    this.customerEmail = customerEmail;
    this.customerCountry = customerCountry;
    this.customerCity = customerCity;
    this.customerZip = customerZip;
    this.customerStreet = customerStreet;
    this.customerHouse = customerHouse;
    this.customerApartment = customerApartment;
  }
}

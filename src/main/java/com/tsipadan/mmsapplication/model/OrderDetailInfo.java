package com.tsipadan.mmsapplication.model;

import lombok.Data;

@Data
public class OrderDetailInfo {

  private String id;

  private String productCode;
  private String productName;

  private int quantity;
  private double price;
  private double amount;

  public OrderDetailInfo(String id, String productCode,
                         String productName, int quantity,
                         double price, double amount) {
    this.id = id;
    this.productCode = productCode;
    this.productName = productName;
    this.quantity = quantity;
    this.price = price;
    this.amount = amount;
  }

}

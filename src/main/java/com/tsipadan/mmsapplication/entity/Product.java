//package com.tsipadan.mmsapplication.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Data
//@Entity
//@Table(name = "products")
//public class Product {
//
//  @Id
//  @Column(name = "Code", length = 20, nullable = false)
//  private String code;
//
//  @Temporal(TemporalType.DATE)
//  @Column(name = "Create_Date", nullable = false)
//  private Date createDate;
//
//  @Lob
//  @Column(name = "Image", length = Integer.MAX_VALUE)
//  private byte[] image;
//
//  @Column(name = "Name", nullable = false)
//  private String name;
//
//  @Column(name = "Price", nullable = false)
//  private double price;
//
//  @Column(name = "Category", nullable = false)
//  private String category;
//
//  @Column(name = "Size", nullable = false)
//  private String size;
//
//  public Product(){}
//
//}

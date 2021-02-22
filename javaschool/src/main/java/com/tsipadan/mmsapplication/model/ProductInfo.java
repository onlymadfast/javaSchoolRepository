//package com.tsipadan.mmsapplication.model;
//
//import com.tsipadan.mmsapplication.entity.Product;
//import lombok.Data;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//@Data
//public class ProductInfo {
//
//  private String code;
//  private String name;
//  private double price;
//  private String category;
//  private String size;
//
//  private boolean newProduct = false;
//
//  private CommonsMultipartFile fileData;
//
//  public ProductInfo(){}
//
//  public ProductInfo(Product product){
//    this.code = product.getCode();
//    this.name = product.getName();
//    this.price = product.getPrice();
//    this.category = product.getCategory();
//    this.size = product.getSize();
//  }
//
//  public ProductInfo(String code, String name,
//                     double price, String category,
//                     String size) {
//    this.code = code;
//    this.name = name;
//    this.price = price;
//    this.category = category;
//    this.size = size;
//  }
//}

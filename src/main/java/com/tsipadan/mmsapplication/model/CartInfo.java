//package com.tsipadan.mmsapplication.model;
//
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//public class CartInfo {
//
//  private int orderNum;
//
//  private CustomerInfo customerInfo;
//
//  private final List<CartLineInfo> cartLines = new ArrayList<>();
//
//  public CartInfo() {}
//
//  private CartLineInfo findLineByCode(String code){
//    for (CartLineInfo line: this.cartLines) {
//      if (line.getProductInfo().getCode().equals(code)) {
//        return line;
//      }
//    }
//    return null;
//  }
//
//  public void addProduct(ProductInfo productInfo, int quantity) {
//
//    CartLineInfo line = this.findLineByCode(productInfo.getCode());
//    if (line == null) {
//      line = new CartLineInfo();
//      line.setQuantity(0);
//      line.setProductInfo(productInfo);
//      this.cartLines.add(line);
//    }
//    int newQuantity = line.getQuantity() + quantity;
//    if (newQuantity <= 0) {
//      this.cartLines.remove(line);
//    } else {
//      line.setQuantity(newQuantity);
//    }
//  }
//
//  public void updateProduct(String code, int quantity) {
//    CartLineInfo line = this.findLineByCode(code);
//    if (line != null) {
//      if (quantity <= 0) {
//        this.cartLines.remove(line);
//      } else {
//        line.setQuantity(quantity);
//      }
//    }
//  }
//
//  public void removeProduct(ProductInfo productInfo) {
//    CartLineInfo line = this.findLineByCode(productInfo.getCode());
//    if (line != null) {
//      this.cartLines.remove(line);
//    }
//  }
//
//  public boolean isEmpty() {
//    return this.cartLines.isEmpty();
//  }
//
//  public boolean isValidCustomer() {
//    return this.customerInfo != null && this.customerInfo.isValid();
//  }
//
//  public int getQuantityTotal() {
//    int quantity = 0;
//    for (CartLineInfo line : this.cartLines) {
//      quantity += line.getQuantity();
//    }
//    return quantity;
//  }
//
//  public void updateQuantity(CartInfo cartForm) {
//    if (cartForm != null) {
//      List<CartLineInfo> lines = cartForm.getCartLines();
//      for (CartLineInfo line : lines) {
//        this.updateProduct(line.getProductInfo().getCode(), line.getQuantity());
//      }
//    }
//  }
//
//  public double getAmountTotal() {
//    double total = 0;
//    for (CartLineInfo line : this.cartLines) {
//      total += line.getAmount();
//    }
//    return total;
//  }
//
//}

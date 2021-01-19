package com.tsipadan.mmsapplication.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetail {

  @Id
  @Column(name = "ID")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "order_details_orders_ID_fk"))
  private Order order;

  @Column(name = "Amount", nullable = false)
  private double amount;

  @Column(name = "Price", nullable = false)
  private double price;

  @Column(name = "Quantity", nullable = false)
  private int quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "order_detail_products_Code_fk"))
  private Product product;

}

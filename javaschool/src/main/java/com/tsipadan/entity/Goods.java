package com.tsipadan.entity;

import com.tsipadan.enumaration.ItemSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "goods")
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "name")
  private String itemName;

  @Column(name = "price")
  private Integer itemPrice;

  @Column(name = "quantity")
  private Integer itemQuantity;

  @Lob
  @Column(name = "image", length = Integer.MAX_VALUE)
  private byte[] image;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "itemCategory_ID", foreignKey = @ForeignKey(name = "goods_itemcategory_ID_fk"))
  private ItemCategory itemCategory;

  @Enumerated(EnumType.STRING)
  @Column(name = "itemSize")
  private ItemSize itemSize;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userOrder_ID", foreignKey = @ForeignKey(name = "goods_userOrder_FK"))
  private UserOrder userOrder;

}
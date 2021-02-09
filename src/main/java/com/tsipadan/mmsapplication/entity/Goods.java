package com.tsipadan.mmsapplication.entity;

import lombok.Data;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "goods")
public class Goods {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private long id;

  @Column(name = "name")
  private String itemName;

  @Column(name = "price")
  private int itemPrice;

  @Column(name = "quantity")
  private int itemQuantity;

  @Lob
  @Column(name = "image", length = Integer.MAX_VALUE)
  private CommonsMultipartFile image;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "category_goods",
      joinColumns = {@JoinColumn(name = "goods_ID", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "category_ID", referencedColumnName = "id")})
  private List<ItemCategory> categories;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "sizeTable_ID", foreignKey = @ForeignKey(name = "goods_sizeTable_FK"))
  private SizeTable sizeTable;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userOrder_ID", foreignKey = @ForeignKey(name = "goods_userOrder_FK"))
  private UserOrder userOrder;


}
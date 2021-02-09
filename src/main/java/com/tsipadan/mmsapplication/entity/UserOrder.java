package com.tsipadan.mmsapplication.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "userorder")
public class UserOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "howPay_ID", foreignKey = @ForeignKey(name = "userorder_howPay_FK"))
  private HowPay howPay;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "howDeliver_ID", foreignKey = @ForeignKey(name = "userorder_howDeliver_FK"))
  private HowDeliver howDeliver;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "userOrder", cascade = CascadeType.ALL)
  private List<Goods> listOfGoods;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "statusPay_ID", foreignKey = @ForeignKey(name = "userorder_statusPay_FK"))
  private StatusPay statusPay;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "statusOrder_ID", foreignKey = @ForeignKey(name = "userorder_statusOrder_FK"))
  private StatusOrder statusOrder;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userAddress_ID", foreignKey = @ForeignKey(name = "userorder_userAddress_FK"))
  private UserAddress userAddress;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userInfo_ID", foreignKey = @ForeignKey(name = "userorder_userInfo_FK"))
  private User user;

}

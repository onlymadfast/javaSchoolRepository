package com.tsipadan.mmsapplication.entity;

import com.tsipadan.enumaration.HowDeliver;
import com.tsipadan.enumaration.HowPay;
import com.tsipadan.enumaration.StatusOrder;
import com.tsipadan.enumaration.StatusPay;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "userorder")
public class UserOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "orderNum")
  private String orderNum;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "orderDate")
  @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
  private Date orderDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "howPay")
  private HowPay howPay;

  @Enumerated(EnumType.STRING)
  @Column(name = "howDeliver")
  private HowDeliver howDeliver;

  @Enumerated(EnumType.STRING)
  @Column(name = "statusPay")
  private StatusPay statusPay;

  @Enumerated(EnumType.STRING)
  @Column(name = "statusOrder")
  private StatusOrder statusOrder;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "userOrder", cascade = CascadeType.ALL)
  private List<Goods> listOfGoods;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userAddress_ID", foreignKey = @ForeignKey(name = "userorder_userAddress_FK"))
  private UserAddress userAddress;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userInfo_ID", foreignKey = @ForeignKey(name = "userorder_userInfo_FK"))
  private User user;

  @Column(name = "amount")
  private double amount;

}

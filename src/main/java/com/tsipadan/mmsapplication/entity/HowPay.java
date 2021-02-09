package com.tsipadan.mmsapplication.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "howPay")
public class HowPay {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private long id;

  @Column(name = "howPayOrder")
  private String howPayOrder;

}

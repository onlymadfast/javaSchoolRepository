package com.tsipadan.mmsapplication.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "statuspay")
public class StatusPay {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private long id;

  @Column(name = "statusPayOrder")
  private String statusPayOrder;

}

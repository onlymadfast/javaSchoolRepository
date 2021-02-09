package com.tsipadan.mmsapplication.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "howDeliver")
public class HowDeliver {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private long id;

  @Column(name = "howDeliverOrder")
  private String howDeliverOrder;

}
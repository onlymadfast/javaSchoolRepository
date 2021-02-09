package com.tsipadan.mmsapplication.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "statusorder")
public class StatusOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private long id;

  @Column(name = "statusOrder")
  private String statusOrder;

}

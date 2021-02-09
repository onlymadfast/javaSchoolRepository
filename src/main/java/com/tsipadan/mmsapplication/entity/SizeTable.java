package com.tsipadan.mmsapplication.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sizetable")
public class SizeTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private long id;

  @Column(name = "itemSize")
  private String itemSize;

}

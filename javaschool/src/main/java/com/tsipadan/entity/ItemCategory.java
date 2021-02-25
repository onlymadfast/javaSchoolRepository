package com.tsipadan.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "itemCategory")
public class ItemCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "itemCategory")
  private String itemCategory;

}

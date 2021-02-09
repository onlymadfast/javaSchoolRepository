package com.tsipadan.mmsapplication.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "category")
public class ItemCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private long id;

  @Column(name = "category")
  private String name;

  @Transient
  @ManyToMany(mappedBy = "categories")
  private Set<Goods> goods;

}

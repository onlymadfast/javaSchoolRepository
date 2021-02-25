package com.tsipadan.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "useraddress")
public class UserAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "userCountry")
  private String userCountry;

  @Column(name = "userCity")
  private String userCity;

  @Column(name = "userZip")
  private String userZip;

  @Column(name = "userStreet")
  private String userStreet;

  @Column(name = "userHouse")
  private Integer userHouse;

  @Column(name = "userApartment")
  private Integer userApartment;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "userInfo", foreignKey = @ForeignKey(name = "useraddress_users_userName_fk"))
  private User user;

}

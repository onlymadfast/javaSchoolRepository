package com.tsipadan.mmsapplication.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Data
public class Account {

  public static final String ROLE_MANAGER = "CUSTOMER";
  public static final String ROLE_EMPLOYEE = "ADMINISTRATOR";

  @Id
  @Column(name = "User_name", length = 20, nullable = false)
  private String userName;

  @Column(name = "Password", length = 20, nullable = false)
  private String password;

  @Column(name = "Active", length = 1, nullable = false)
  private boolean active;

  @Column(name = "User_Role", length = 20, nullable = false)
  private String userRole;

}

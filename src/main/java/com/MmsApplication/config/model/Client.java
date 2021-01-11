package com.MmsApplication.config.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clients")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @ToString.Exclude
  @OneToOne(mappedBy = "clients")
  private ClientAddress clientAddress;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "birthday")
  private String birthday;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

}



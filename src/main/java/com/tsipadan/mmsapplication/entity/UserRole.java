package com.tsipadan.mmsapplication.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "user_role")
public class UserRole implements GrantedAuthority {

  @Id
  @Column(name = "ID")
  private long id;

  @Column(name = "userRole")
  private String userRole;

  @Transient
  @ManyToMany(mappedBy = "roles")
  private Set<User> users;

  public UserRole(){}

  public UserRole(Long id){
    this.id = id;
  }

  public UserRole(Long id, String userRole){
    this.id=id;
    this.userRole=userRole;
  }

  @Override
  public String getAuthority() {
    return getUserRole();
  }
}

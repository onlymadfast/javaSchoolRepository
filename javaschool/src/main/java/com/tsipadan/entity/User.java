package com.tsipadan.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "userName")})
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @NotNull(message = "Name cannot be null")
  @Column(name = "userName")
  private String username;

  @NotNull(message = "Password cannot be null")
  @Column(name = "userPassword")
  private String password;

  @Column(name = "userFirstName")
  private String userFirstName;

  @Column(name = "userLastName")
  private String userLastName;

  @Temporal(TemporalType.DATE)
  @Column(name = "userBirthday")
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private Date userBirthday;

  @Email(message = "Email should be valid")
  @Column(name = "userEmail")
  private String userEmail;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_userRole",
      joinColumns = {@JoinColumn(name = "userID", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "userRole_ID", referencedColumnName = "id")})
  private Set<UserRole> userRoles;

//  @OneToOne(mappedBy = "user")
//  private UserAddress userAddress;

//  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
//  private List<UserOrder> userOrderList;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getUserRoles();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}

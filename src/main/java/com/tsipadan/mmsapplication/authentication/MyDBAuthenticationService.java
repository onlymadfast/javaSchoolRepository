package com.tsipadan.mmsapplication.authentication;

import com.tsipadan.mmsapplication.dao.AccountDAO;
import com.tsipadan.mmsapplication.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyDBAuthenticationService implements UserDetailsService {

  @Autowired
  private final AccountDAO accountDAO;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountDAO.findAccount(username);
    System.out.println("Account= " + account);

    if (account == null) {
      throw new UsernameNotFoundException("User " //
          + username + " was not found in the database");
    }

    String role = account.getUserRole();

    List<GrantedAuthority> grantList = new ArrayList<>();

    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

    grantList.add(authority);

    boolean enabled = account.isActive();
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    UserDetails userDetails = new User(account.getUserName(),
        account.getPassword(), enabled, accountNonExpired,
        credentialsNonExpired, accountNonLocked, grantList);

    return userDetails;
  }

}

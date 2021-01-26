package com.tsipadan.mmsapplication.authentication;

import com.tsipadan.mmsapplication.dao.AccountDAO;
import com.tsipadan.mmsapplication.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

  private final AccountDAO accountDAO;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

    Account account = accountDAO.findAccount(userName);
    if (account == null) {
      System.out.println("User not found! " + userName);
      throw new UsernameNotFoundException("User " + userName + " was not found in the database");
    }
    System.out.println("Account= " + account);

    String role = account.getUserRole();
    List<GrantedAuthority> grantList = new ArrayList<>();
    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
    grantList.add(authority);

    boolean enabled = account.isActive();

    return new User(account.getUserName(), account.getPassword(),
        enabled, true,true,true, grantList);

  }

}

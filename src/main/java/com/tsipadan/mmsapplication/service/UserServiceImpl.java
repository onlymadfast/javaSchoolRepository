package com.tsipadan.mmsapplication.service;

import com.tsipadan.mmsapplication.dao.AccountDAO;
import com.tsipadan.mmsapplication.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final AccountDAO accountDAO;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void saveUser(Account account) {
    Account account1 = new Account();
    account1.setUserName(account.getUserName());
    account1.setPassword(passwordEncoder.encode(account.getPassword()));
    account1.setActive(account.isActive());
    account1.setUserRole(account.getUserRole());
    accountDAO.saveAccount(account1);
  }

  @Override
  public void updatePassword(String newPassword, String userName){
    Account account1 = accountDAO.findAccount(userName);
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(newPassword);
    account1.setPassword(encodedPassword);
    accountDAO.mergeAccount(account1);
  }

}

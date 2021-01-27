package com.tsipadan.mmsapplication.service;

import com.tsipadan.mmsapplication.entity.Account;

public interface UserService {

  void saveUser(Account account);

  void updatePassword(String newPassword, String userName);

}

package com.tsipadan.mmsapplication.dao;

import com.tsipadan.mmsapplication.entity.Account;

public interface AccountDAO {

  Account findAccount(String userName);

}

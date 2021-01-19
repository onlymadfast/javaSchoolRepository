package com.tsipadan.mmsapplication.dao.impl;

import com.tsipadan.mmsapplication.dao.AccountDAO;
import com.tsipadan.mmsapplication.entity.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class AccountDAOImpl implements AccountDAO {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Account findAccount(String userName) {
    TypedQuery<Account> query = entityManager.createQuery("SELECT userName FROM Account", Account.class);
    List<Account> results = query.getResultList();
    return (Account) results;
  }

}

//package com.tsipadan.mmsapplication.dao.impl;
//
//import com.tsipadan.mmsapplication.dao.AccountDAO;
//import com.tsipadan.mmsapplication.entity.Account;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//@Repository
//@Transactional
//public class AccountDAOImpl implements AccountDAO {
//
//  @PersistenceContext
//  private EntityManager entityManager;
//
//  @Override
//  public Account findAccount(String userName) {
//      try{
//        String sql = "SELECT u from Account u WHERE u.userName = :userName";
//        Query query = entityManager.createQuery(sql,Account.class);
//        query.setParameter("userName", userName);
//        return (Account) query.getSingleResult();
//      } catch (NoResultException e){
//        return null;
//      }
//  }
//
//  @Override
//  public void saveAccount(Account account) {
//    this.entityManager.persist(account);
//  }
//
//  @Override
//  public void mergeAccount(Account account){
//    this.entityManager.merge(account);
//  }
//
//}

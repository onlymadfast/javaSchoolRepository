//package com.tsipadan.mmsapplication.dao.impl;
//
//import com.tsipadan.mmsapplication.dao.ClientDAO;
//import com.tsipadan.mmsapplication.model.Client;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@Transactional
//@Repository
//public class ClientDAOImpl implements ClientDAO {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public void saveClientAndAddress(Client client) {
//        this.entityManager.persist(client);
//    }
//
//    @Override
//    public Client findClient(String email) {
//        return (Client) entityManager
//            .createQuery("SELECT email FROM Client", Client.class)
//            .getResultList();
//    }
//}

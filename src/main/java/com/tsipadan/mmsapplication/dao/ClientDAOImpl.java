package com.tsipadan.mmsapplication.dao;

import com.tsipadan.mmsapplication.model.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ClientDAOImpl implements ClientDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveClientAndAddress(Client client) {
        this.entityManager.persist(client);
    }

    @Override
    public Client findByFirstName(String firstName) {
        return (Client) entityManager
            .createQuery("SELECT firstName FROM Client", Client.class)
            .getResultList();
    }
}

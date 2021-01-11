package com.MmsApplication.config.dao;

import com.MmsApplication.config.dto.ClientRegistrationDto;
import com.MmsApplication.config.model.Client;
import com.MmsApplication.config.model.ClientAddress;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ClientsDAOImpl implements ClientDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ClientRegistrationDto saveClientAndAddress(Client client, ClientAddress clientAddress) {
        this.entityManager.persist(client);
        this.entityManager.persist(clientAddress);

        return null;
    }

    @Override
    public Client findByFirstName(String firstName) {
        return (Client) entityManager
            .createQuery("SELECT firstName FROM Client", Client.class)
            .getResultList();
    }
}

package com.tsipadan.mmsapplication.dao;

import com.tsipadan.mmsapplication.model.Client;

public interface ClientDAO {

    void saveClientAndAddress (Client client);

    Client findByFirstName(String firstName);
}

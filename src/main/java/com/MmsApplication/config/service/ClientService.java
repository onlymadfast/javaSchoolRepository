package com.MmsApplication.config.service;

import com.MmsApplication.config.model.Client;
import com.MmsApplication.config.model.ClientAddress;

public interface ClientService {

    void saveClientAndAddressByDto (Client client, ClientAddress clientAddress);

    Client findByFirstName(String firstName);

}

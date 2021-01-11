package com.MmsApplication.config.dao;

import com.MmsApplication.config.dto.ClientRegistrationDto;
import com.MmsApplication.config.model.Client;
import com.MmsApplication.config.model.ClientAddress;

public interface ClientDAO {

    ClientRegistrationDto saveClientAndAddress (Client client, ClientAddress clientAddress);

    Client findByFirstName(String firstName);
}

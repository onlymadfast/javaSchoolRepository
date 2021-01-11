package com.MmsApplication.config.service;

import com.MmsApplication.config.dao.ClientDAO;
import com.MmsApplication.config.dto.ClientRegistrationDto;
import com.MmsApplication.config.model.Client;
import com.MmsApplication.config.model.ClientAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

  private final ClientDAO clientDAO;

//    @Transactional
//    @Override
//    public void saveClientAndAddress(Client client, ClientAddress clientAddress) {
//        clientDAO.saveClientAndAddress(client,clientAddress);
//    }

  @Transactional
  @Override
  public void saveClientAndAddressByDto(Client client, ClientAddress clientAddress){
    ClientRegistrationDto clientRegistrationDto = clientDAO.saveClientAndAddress(client, clientAddress);
    Client cl = clientRegistrationDto._toConvertClientEntity();
  }

  @Override
  public Client findByFirstName(String firstName) {
    return clientDAO.findByFirstName(firstName);
  }

}

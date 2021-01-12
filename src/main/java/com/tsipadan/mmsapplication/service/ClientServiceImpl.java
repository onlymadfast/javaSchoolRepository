package com.tsipadan.mmsapplication.service;

import com.tsipadan.mmsapplication.dao.ClientDAO;
import com.tsipadan.mmsapplication.dto.ClientDto;
import com.tsipadan.mmsapplication.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

  private final ClientDAO clientDAO;

  @Transactional
  @Override
  public void saveClientAndAddressByDto(ClientDto clientDto){
    final Client client = clientDto.ConvertClientToEntity();
    clientDAO.saveClientAndAddress(client);
  }

  @Override
  public Client findByFirstName(String firstName) {
    return clientDAO.findByFirstName(firstName);
  }

}

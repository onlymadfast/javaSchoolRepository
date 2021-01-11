package com.MmsApplication.config.dto;

import com.MmsApplication.config.model.Client;
import com.MmsApplication.config.model.ClientAddress;
import lombok.Data;

@Data
public class ClientRegistrationDto {

  private int id;
  private ClientAddress address;
  private String firstName;
  private String lastName;
  private String birthday;
  private String email;
  private String password;

  public Client _toConvertClientEntity() {
    Client client = new Client();
    client.setId(client.getId());
    client.setClientAddress(client.getClientAddress());
    client.setFirstName(client.getFirstName());
    client.setLastName(client.getLastName());
    client.setEmail(client.getEmail());
    client.setPassword(client.getPassword());
    return client;
  }

}

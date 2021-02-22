package com.tsipadan.service.impl;

import com.tsipadan.service.api.RestService;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Stateless
public class RestServiceImpl implements RestService {

  @Override
  public String executeRequest(String targetUrl) {
    HttpURLConnection connection = null;
    try {
      URL url = new URL(targetUrl);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Content-Type", "application/dto");
      if (connection.getResponseCode() != 200) {
        throw new IOException();
      }

      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String dto = br.readLine();
      br.close();
      return dto;
    } catch (Exception e) {
      StringWriter stringWriter = new StringWriter();
      e.printStackTrace(new PrintWriter(stringWriter));
      log.error(stringWriter.toString());
      return null;
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
  }

}

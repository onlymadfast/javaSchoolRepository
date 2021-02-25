package com.tsipadan.service.implementation;

import com.tsipadan.service.api.JMSSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JMSSenderServiceImpl implements JMSSenderService {

  private final JmsTemplate jmsTemplate;

  @Override
  public void sendMessage() {
    try {
      jmsTemplate.send(session -> session.createTextMessage("Update the table"));
      log.info("JMS was send message");
    } catch (RuntimeException e) {
      log.warn("Unable to sent a JMS");
      log.warn(e.getMessage());
    }
  }
}

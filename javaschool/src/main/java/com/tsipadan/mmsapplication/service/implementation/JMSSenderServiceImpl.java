package com.tsipadan.mmsapplication.service.implementation;

import com.tsipadan.mmsapplication.service.api.JMSSenderService;
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
      log.info("jms was send");
    } catch (RuntimeException e) {
      log.warn("Unable to sent a jms");
      log.warn(e.getMessage());
    }
  }
}

package com.tsipadan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsipadan.dto.UserOrderDTO;
import com.tsipadan.service.api.RestService;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Named("memoryBean")
@ApplicationScoped
public class MemoryBean {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Inject
  private RestService restService;

  @Inject
  @Push
  private PushContext push;

  private List<UserOrderDTO> userOrderDtoList;

  @PostConstruct
  public void init(){
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover://(tcp://localhost:61616");
    Connection connection;
    try {
      connection = connectionFactory.createConnection();
      connection.start();
      Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
      Queue queue = session.createQueue("testQ");
      MessageConsumer consumer = session.createConsumer(queue);
      consumer.setMessageListener(message -> {
        if (message instanceof TextMessage){
          TextMessage msg = (TextMessage) message;
          try {
            log.info("Received Message from queue: " + msg.getText());
            refreshData();
          } catch (JMSException | IOException e){
            e.printStackTrace();
          }
        } else {
          log.warn("Message of wrong type: " + message.getClass().getName());
        }
      });
    } catch (JMSException e) {
      e.printStackTrace();
    }

  }

  private void refreshData() throws IOException {

    userOrderDtoList = new LinkedList<>();
    userOrderDtoList.addAll(Arrays.asList(objectMapper.readValue(
        restService.executeRequest("http://localhost:8081/secondapp/orders"),
        UserOrderDTO[].class)));
    push.send("updateOrders");

  }

  public List<UserOrderDTO> getUserOrderDtoList() {
    return userOrderDtoList;
  }

  public void setUserOrderDtoList(List<UserOrderDTO> userOrderDtoList) {
    this.userOrderDtoList = userOrderDtoList;
  }

}

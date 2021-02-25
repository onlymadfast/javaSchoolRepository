package com.tsipadan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsipadan.dto.GoodsDTO;
import com.tsipadan.dto.UserDTO;
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
  private List<UserDTO> userDTOList;
  private List<GoodsDTO> goodsDTOList;

  @PostConstruct
  public void init() {
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
    Connection connection;
    try {
      connection = connectionFactory.createConnection();
      connection.start();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      Queue queue = session.createQueue("testQ");
      MessageConsumer consumer = session.createConsumer(queue);
      consumer.setMessageListener(message -> {
        if (message instanceof TextMessage) {
          TextMessage msg = (TextMessage) message;
          try {
            log.info("Received Message from queue: " + msg.getText());
            refreshData();
          } catch (JMSException | IOException e) {
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

    userDTOList = new LinkedList<>();
    userDTOList.addAll(Arrays.asList(objectMapper.readValue(
        restService.executeRequest("http://localhost:8081/secondapp/users"),
        UserDTO[].class)));
    push.send("updateUsers");

    goodsDTOList = new LinkedList<>();
    goodsDTOList.addAll(Arrays.asList(objectMapper.readValue(
        restService.executeRequest("http://localhost:8081/secondapp/goods"),
        GoodsDTO[].class)));
    push.send("updateGoods");


  }

  public List<UserOrderDTO> getUserOrderDtoList() {
    return userOrderDtoList;
  }

  public void setUserOrderDtoList(List<UserOrderDTO> userOrderDtoList) {
    this.userOrderDtoList = userOrderDtoList;
  }

  public List<UserDTO> getUserDTOList() {
    return userDTOList;
  }

  public void setUserDTOList(List<UserDTO> userDTOList) {
    this.userDTOList = userDTOList;
  }

  public List<GoodsDTO> getGoodsDTOList() {
    return goodsDTOList;
  }

  public void setGoodsDTOList(List<GoodsDTO> goodsDTOList) {
    this.goodsDTOList = goodsDTOList;
  }
}

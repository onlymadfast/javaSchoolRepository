package com.tsipadan.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import java.util.Arrays;

@Configuration
public class JmsConfiguration {

  private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";

  private static final String ORDER_QUEUE = "testQ";

  @Bean
  public ActiveMQConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);
    connectionFactory.setTrustedPackages(Arrays.asList("com.websystique.spring", "java.common"));
    return connectionFactory;
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    JmsTemplate template = new JmsTemplate();
    template.setConnectionFactory(connectionFactory());
    template.setDefaultDestinationName(ORDER_QUEUE);
    return template;
  }

}

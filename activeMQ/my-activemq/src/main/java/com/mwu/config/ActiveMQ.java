package com.mwu.config;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.Collections;

@Configuration
public class ActiveMQ {
//    @Value("${spring.activemq.broker-url}")
//    private String activeMQBrokerUrl;
//
//    @Value("${spring.activemq.user}")
//    private String activeMQBrokerUsername;
//
//    @Value("${spring.activemq.password}")
//    private String activeMQBrokerPassword;
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(
//                activeMQBrokerUsername,
//                activeMQBrokerPassword,
//                activeMQBrokerUrl
//        );
//        activeMQConnectionFactory. setTrustedPackages(Collections.singletonList("com.mwu.controller"));
//        return activeMQConnectionFactory;
//    }
//
//    @Bean
//    public JmsOperations jmsOperations() {
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setConnectionFactory(connectionFactory());
//        return jmsTemplate;
//    }
//
//    @Bean
//    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory(DefaultJmsListenerContainerFactoryConfigurer configurer) {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        configurer.configure(factory, connectionFactory());
//        factory.setMessageConverter(messageConverter());
//        return factory;
//    }
//
//    public MessageConverter messageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.OBJECT);
//        return converter;
//    }


    @Bean
    public JmsListenerContainerFactory<?> jmsFactory(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(jacksonJmsMessageConverter());
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    /**
     * Serialize message content to json using TextMessage
     *
     * @return Message Converter
     */
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_asb_");
        return converter;
    }
}

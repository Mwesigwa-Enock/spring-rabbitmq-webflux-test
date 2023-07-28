package com.backend.inventotest.services;

import com.backend.inventotest.models.TestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.rabbitmq.client.Delivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.Receiver;

import javax.annotation.PostConstruct;

@Component
@EnableRabbit
public class ConsumerService implements IConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Value("${spring.rabbitmq.queue}")
    private String Queue;
    private final Receiver receiver;
    private final ObjectMapper objectMapper;

    public ConsumerService(Receiver receiver, ObjectMapper objectMapper) {
        this.receiver = receiver;
        this.objectMapper = objectMapper;
    }


    @Override
    public Flux<TestModel> receiveMessages() {
        return receiver.consumeAutoAck(Queue)
                .mapNotNull(this::deserializeMessage);

    }

    private TestModel deserializeMessage(Delivery delivery) {
        try {
            String message = new String(delivery.getBody());
            TestModel result = objectMapper.readValue(message, TestModel.class);
            logger.info("Received message {}", result);
            return result;
        } catch (Exception e) {
            logger.error("An error occurred while reading message - {}", e.getMessage());
            return null;
        }
    }

    @PostConstruct
    public void startConsumingMessages() {
        receiveMessages().subscribe();
    }
}

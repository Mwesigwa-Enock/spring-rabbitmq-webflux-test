package com.backend.inventotest.services;

import com.backend.inventotest.models.TestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

@Service
public class PublisherService implements IPublisherService {
    private static final Logger logger = LoggerFactory.getLogger(PublisherService.class);

    @Value("${spring.rabbitmq.queue}")
    private String Queue;

    private final Sender sender;

    private final ObjectMapper objectMapper;


    public PublisherService(Sender sender, ObjectMapper objectMapper) {
        this.sender = sender;
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<String> publishMessage(TestModel testModel) {
        try {
            byte[] messageBytes = objectMapper.writeValueAsBytes(testModel);
            OutboundMessage message = new OutboundMessage("", Queue, messageBytes);
            logger.info("Publishing message to the queue - "+Queue+"....");
            return sender.send(Mono.just(message))
                    .then(Mono.just("Message Queued Successfully!"))
                    .doOnSuccess(m -> logger.info("Message Published Successfully"));
        } catch (Exception e) {
            return Mono.error(e);
        }
    }
}

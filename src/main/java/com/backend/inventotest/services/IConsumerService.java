package com.backend.inventotest.services;

import com.backend.inventotest.models.TestModel;
import reactor.core.publisher.Flux;

public interface IConsumerService {

    Flux<TestModel> receiveMessages();

}

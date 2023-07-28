package com.backend.inventotest.services;

import com.backend.inventotest.models.TestModel;
import reactor.core.publisher.Mono;

public interface IPublisherService {

    Mono<String> publishMessage(TestModel testModel);
}

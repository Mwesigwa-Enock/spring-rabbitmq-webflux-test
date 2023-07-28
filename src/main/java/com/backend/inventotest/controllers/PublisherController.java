package com.backend.inventotest.controllers;

import com.backend.inventotest.models.TestModel;
import com.backend.inventotest.services.PublisherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/")
@Api(tags = "Consumer Controller", value = "ConsumerController")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/queue-message")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Test Operation")
    public Mono<String> uploadMessage(@RequestBody TestModel testModel){
        return publisherService.publishMessage(testModel);
    }
}

package com.backend.inventotest;

import com.backend.inventotest.models.TestModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class TestController {

    @LocalServerPort
    private int port;

    @Autowired
    private WebClient.Builder webClientBuilder;


    @Test
    public void testPublishMessage() {
        TestModel model1 = new TestModel();
        model1.setName("Alien Skin");
        model1.setAge(34);
        model1.setDateOfBirth(new Date());

        TestModel model2 = new TestModel();
        model2.setName("Enock Mwesigwa");
        model2.setAge(40);
        model2.setDateOfBirth(new Date());


        TestModel model3 = new TestModel();
        model3.setName("Ruth Kalibala");
        model3.setAge(55);
        model3.setDateOfBirth(new Date());

        List<TestModel> modelList = Arrays.asList(model1, model2, model3);
        Flux<TestModel> modelFlux = Flux.fromIterable(modelList);

        // Send objects using WebClient
        WebClient webClient = webClientBuilder
                .baseUrl("http://localhost:" + port).build();

        Flux<String> response = modelFlux
                .flatMap(object -> webClient.post()
                        .uri("/v1/queue-message")
                        .body(BodyInserters.fromValue(object))
                        .retrieve()
                        .bodyToMono(String.class));
        // Expect three responses for three MyObjects
        StepVerifier.create(response)
                .expectNext("Message Queued Successfully!")
                .expectNext("Message Queued Successfully!")
                .expectNext("Message Queued Successfully!")
                .expectComplete()
                .verify();
    }

}

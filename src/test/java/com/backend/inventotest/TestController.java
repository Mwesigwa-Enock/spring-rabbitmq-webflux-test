package com.backend.inventotest;

import com.backend.inventotest.models.TestModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class TestController {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testPublishMessage(){
        TestModel model = new TestModel();
        model.setName("Alien Skin");
        model.setAge(34);
        model.setDateOfBirth(new Date());

        ResponseEntity<String> response =  restTemplate
                .postForEntity("/v1/queue-message", model, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Message Queued Successfully!", response.getBody());
    }


}

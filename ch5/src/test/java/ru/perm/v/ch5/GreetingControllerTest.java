package ru.perm.v.ch5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class GreetingControllerTest {

    @Test
    void getGreeting(@Autowired WebTestClient client) {
        client.get().uri("/greeting/").exchange().expectStatus().isOk();
//        .expectBody(Iterable.class).returnResult().getResponseBody().iterator().hasNext();
    }

    @Test
    void testGetGreeting() {
    }
}

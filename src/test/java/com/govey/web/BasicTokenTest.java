package com.govey.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicTokenTest {

    @LocalServerPort
    int port;

    private RestTemplate restTemplate = new RestTemplate();

    @DisplayName("1. Basic Token Test")
    @Test
    void test_1(){

        String url = format("http://localhost:%d%s", port, "/greeting");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "basic "+ Base64.getEncoder().encodeToString("user1:1111".getBytes()));
        HttpEntity entity = new HttpEntity("", headers);

        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.GET, entity, String.class);

        assertEquals("Hello jy", response.getBody());
    }

}

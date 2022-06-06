package com.aycap.kbb.batchdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class BatchDemoTest {
    @Autowired
    private TestRestTemplate testTemplate;

    @LocalServerPort
    private int port;

    private String url() {
        return "http://localhost:" + port + "/invoke-job/post";
    }

    @Test
    void testPostApplicationHttpStatus(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(new ClassPathResource("mock.json"),headers);
        assertEquals(HttpStatus.OK,this.testTemplate.postForEntity(url(),entity,String.class).getStatusCode());
    }

    @Test
    void testPostApplicationResponse(){
        HashMap<String,Object> expected = new HashMap<>();
        ArrayList<Object> mockList = new ArrayList<>();
        expected.put("success_count",2);
        expected.put("error_count",0);
        expected.put("errors",mockList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(new ClassPathResource("mock.json"),headers);
        assertEquals(expected.,this.testTemplate.postForEntity(url(),entity,String.class).getBody());
    }


}

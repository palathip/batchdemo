package com.aycap.kbb.batchdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class BatchDemoTest {
    @Autowired
    private TestRestTemplate testTemplate;

    @LocalServerPort
    private int port;

    private String url() {
        return "http://localhost:" + port + "/v1/api/applications";
    }


}

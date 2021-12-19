package com.example.webfluxstudy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

@SpringBootTest
@ContextConfiguration
@ExtendWith(SpringExtension.class)
class WebFluxStudyApplicationTests {

    @Test
    void contextLoads() {

    }

}

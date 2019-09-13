package com.aquaman.security.gateway;

import com.aquaman.security.gateway.service.ConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AquamanGatewayOsApplicationTests {

    @Autowired
    private ConsumerService consumerService;

    @Test
    public void contextLoads() {
        String result = consumerService.getUserByAccount("test3");
        System.out.println(result);
    }

}

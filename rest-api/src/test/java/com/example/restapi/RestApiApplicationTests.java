package com.example.restapi;

import com.example.restapi.model.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestApiApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() throws JsonProcessingException {
        var user = new UserRequest();
        user.setUserName("홍");
        user.setUserAge(10);
        user.setEmail("hong@naver");
        user.setIsKorean(true);

        var json = objectMapper.writeValueAsString(user);   // 직렬화
        System.out.println(json);

        var dto = objectMapper.readValue(json, UserRequest.class);  // 역직렬화
        System.out.println(dto);
    }

}

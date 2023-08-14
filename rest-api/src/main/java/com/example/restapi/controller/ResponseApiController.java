package com.example.restapi.controller;

import com.example.restapi.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ResponseApiController {
    @GetMapping("")
    public ResponseEntity<UserRequest> user() {
        var user = new UserRequest();
        user.setUserName("홍");
        user.setUserAge(10);
        user.setEmail("hong@naver");

        log.info("user : {}", user);

        var response = ResponseEntity
                .status(HttpStatus.CREATED)
                .header("x-custom", "hi")
                .body(user);

        return response;
    }
}

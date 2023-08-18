package com.example.cookie.controller;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {
    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserDto me(
            HttpServletRequest httpServletRequest,
            @CookieValue(name = "authorization-cookie", required = false)
            String authorizationCookie
    ) {
        log.info("authorizationCookie : {}", authorizationCookie);

        var optionUserDto = userRepository.findById(authorizationCookie);

        return optionUserDto.get();

/*        var cookies = httpServletRequest.getCookies();

        if (cookies != null) {
            for(Cookie cookie: cookies) {
                log.info("key: {}, value: {}", cookie.getName(), cookie.getValue());
            }
        }*/
//        return null;
    }

    @GetMapping("/me2")
    public UserDto me2(
            @RequestHeader(name = "authorization", required = false)
            String authorizationHeader
    ) {
        log.info("authorizationCookie : {}", authorizationHeader);

        var optionUserDto = userRepository.findById(authorizationHeader);

        return optionUserDto.get();

    }
}

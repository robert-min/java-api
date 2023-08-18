package com.example.cookie.db;

import com.example.cookie.model.UserDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserRepository {

    private List<UserDto> userList = new ArrayList<>();

    public Optional<UserDto> findById(String id) {
        return userList
                .stream()
                .filter(it -> {
                    return it.getId().equals(id);
        }).findFirst();
    }

    public Optional<UserDto> findByName(String name) {
        return userList
                .stream()
                .filter(it -> {
                    return it.getName().equals(name);
                }).findFirst();
    }

    @PostConstruct
    public void init() {
        userList.add(
                new UserDto(
                        UUID.randomUUID().toString(),
                        "hong",
                        "1234"
                )
        );
        userList.add(
                new UserDto(
                        UUID.randomUUID().toString(),
                        "park",
                        "1234"
                )
        );
        userList.add(
                new UserDto(
                        UUID.randomUUID().toString(),
                        "kim",
                        "1234"
                )
        );
    }
}

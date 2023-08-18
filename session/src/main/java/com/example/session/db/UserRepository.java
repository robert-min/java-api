package com.example.session.db;

import com.example.session.model.UserDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRepository {
    private List<UserDto> userList = new ArrayList<>();

    public Optional<UserDto> findByName(String name) {
        return userList.stream().filter(it -> {
            return it.getName().equals(name);
        }).findFirst();
    }

    @PostConstruct
    public void init() {
        userList.add(new UserDto("hong", "1234"));
        userList.add(new UserDto("park", "1234"));
        userList.add(new UserDto("kim", "1234"));
    }
}

package com.workflex.controllers;

import com.workflex.domain.dtos.UserDto;
import com.workflex.domain.mappers.UserMapper;
import com.workflex.domain.models.User;
import com.workflex.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository repo;
    private final UserMapper mapper;


    public UserController(UserRepository repo, UserMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return repo.findAll().
                stream().
                map(mapper::toDto).
                collect(Collectors.toList());
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        User user = repo.save(mapper.toEntity(userDto));
        return mapper.toDto(user);
    }
}

package org.mohammed.authorizationserver.controller;

import org.mohammed.authorizationserver.dto.UserGetDto;
import org.mohammed.authorizationserver.dto.UserPostDto;
import org.mohammed.authorizationserver.dto.UserPutDto;
import org.mohammed.authorizationserver.mapper.UserMapper;
import org.mohammed.authorizationserver.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public Page<UserGetDto> findAll(int page, int size) {
        return userService.findAll(page, size).map(userMapper::toDto);
    }

    @GetMapping("/{id}")
    public UserGetDto findById(@PathVariable Long id) {
        return userMapper.toDto(userService.findById(id));
    }

    @PostMapping
    public UserGetDto create(UserPostDto dto) {
        return userMapper.toDto(userService.create(dto));
    }

    @PutMapping("/{id}")
    public UserGetDto update(UserPutDto dto, @PathVariable Long id) {
        return userMapper.toDto(userService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}

package org.mohammed.authorizationserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.mohammed.authorizationserver.dto.*;
import org.mohammed.authorizationserver.mapper.UserMapper;
import org.mohammed.authorizationserver.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<Page<UserGetDto>> findAll(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(userService.findAll(page, size).map(userMapper::toDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toDto(userService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserGetDto> create(@RequestBody UserPostDto dto) {
        log.info("Creating user: {}", dto);
        return ResponseEntity.ok(userMapper.toDto(userService.create(dto)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserGetDto> update(@RequestBody UserPutDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toDto(userService.update(dto, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}

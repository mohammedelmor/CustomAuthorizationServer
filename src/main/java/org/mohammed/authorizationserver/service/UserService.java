package org.mohammed.authorizationserver.service;

import jakarta.transaction.Transactional;
import org.mapstruct.Named;
import org.mohammed.authorizationserver.dto.UserPostDto;
import org.mohammed.authorizationserver.dto.UserPutDto;
import org.mohammed.authorizationserver.exception.UserAlreadyExistsException;
import org.mohammed.authorizationserver.exception.UserNotFoundException;
import org.mohammed.authorizationserver.mapper.UserMapper;
import org.mohammed.authorizationserver.model.User;
import org.mohammed.authorizationserver.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Named("findById")
    public User findById(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + " does not exist");
        }
        return user.get();
    }

    public User findByUsername(String username) {
        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with username: " + username + " does not exist");
        }
        return user.get();
    }

    public Page<User> findAll(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public User create(UserPostDto dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new UserAlreadyExistsException("User " + dto.username() + " already exists");
        }
        return userRepository.save(userMapper.toEntity(dto));
    }

    public User update(UserPutDto dto, Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + " does not exist");
        }
        var updatedEntity = user.map(u -> {
            u.setPassword(dto.password());
            return userRepository.save(u);
        });
        return updatedEntity.get();
    }

    public void delete(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + " does not exist");
        }
        userRepository.delete(user.get());
    }
}

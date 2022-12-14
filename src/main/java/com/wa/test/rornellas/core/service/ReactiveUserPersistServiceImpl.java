package com.wa.test.rornellas.core.service;

import com.wa.test.rornellas.core.exception.UserNotFoundException;
import com.wa.test.rornellas.core.mapper.UserMapper;
import com.wa.test.rornellas.core.service.request.UserRequest;
import com.wa.test.rornellas.core.service.response.UserResponse;
import com.wa.test.rornellas.data.entity.User;
import com.wa.test.rornellas.data.repository.ReactiveUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReactiveUserPersistServiceImpl implements ReactiveUserPersistService {

    private final ReactiveUserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public Mono<UserResponse> create(UserRequest userRequest) {
        User user = this.userMapper.toUserEntity(userRequest);
        user.setCreatedAt(LocalDateTime.now());

        return this.userRepository.save(user)
                .map(this.userMapper::toUserResponse)
                .doFirst(() -> log.info("Starting to create user {} {}", System.lineSeparator(), user))
                .doOnSuccess(__ -> log.info("Finished successfully to create user {} {}", System.lineSeparator(), user));
    }

    @Override
    public Mono<UserResponse> update(Long id, UserRequest userRequest) {
        User user = this.userMapper.toUserEntity(userRequest);
        user.setId(id);
        user.setUpdatedAt(LocalDateTime.now());

        return this.userRepository.findById(id)
                .switchIfEmpty(Mono.error(UserNotFoundException::new))
                .map(foundUser -> {
                    user.setCreatedAt(foundUser.getCreatedAt());
                    return user;
                })
                .flatMap(this.userRepository::save)
                .map(this.userMapper::toUserResponse)
                .doFirst(() -> log.info("Starting to update user with id {}", id))
                .doOnSuccess(unused -> log.info("Finished successfully to update user with id {}", id));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return this.userRepository.findById(id)
                .switchIfEmpty(Mono.error(UserNotFoundException::new))
                .flatMap(user -> this.userRepository.deleteById(id))
                .doFirst(() -> log.info("Starting to delete user with id {}", id))
                .doOnSuccess(unused -> log.info("Finished successfully to delete user with id {}", id));
    }
}

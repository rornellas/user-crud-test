package com.wa.test.rornellas.core.service;

import com.wa.test.rornellas.core.exception.UserNotFoundException;
import com.wa.test.rornellas.core.mapper.UserMapper;
import com.wa.test.rornellas.core.service.response.UserResponse;
import com.wa.test.rornellas.data.entity.UserImpl;
import com.wa.test.rornellas.data.repository.ReactiveUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
public class ReactiveUserFetchServiceTest {

    @MockBean
    ReactiveUserRepository userRepository;

    @MockBean
    UserMapper userMapper;

    ReactiveUserFetchService userFetchService;

    @BeforeEach
    void init() {
        this.userFetchService = new ReactiveUserFetchServiceImpl(this.userRepository, this.userMapper);
    }

    @Test
    @DisplayName("should list users successfully")
    void shouldListUsersSuccessfully() {
        UserImpl user = Mockito.mock(UserImpl.class);
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        Pageable pageable = Pageable.ofSize(10).withPage(0);

        Mockito.when(this.userRepository.findAllBy(pageable))
            .thenReturn(Flux.fromStream(Stream.of(user)));

        Mockito.when(this.userMapper.toUserResponse(user))
            .thenReturn(userResponse);

        StepVerifier.create(this.userFetchService.list(10, 0))
            .expectSubscription()
            .expectNextCount(1)
            .verifyComplete();

        Mockito.verify(this.userRepository, Mockito.times(1)).findAllBy(pageable);
        Mockito.verify(this.userMapper, Mockito.times(1)).toUserResponse(user);
    }

    @Test
    @DisplayName("should find user successfully")
    void shouldFindUserSuccessfully() {
        UserImpl user = Mockito.mock(UserImpl.class);
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        Mockito.when(this.userRepository.findById(ArgumentMatchers.anyLong()))
            .thenReturn(Mono.just(user));

        Mockito.when(this.userMapper.toUserResponse(user))
            .thenReturn(userResponse);

        StepVerifier.create(this.userFetchService.findById(1l))
            .expectSubscription()
            .expectNext(userResponse)
            .verifyComplete();

        Mockito.verify(this.userRepository, Mockito.times(1)).findById(1l);
        Mockito.verify(this.userMapper, Mockito.times(1)).toUserResponse(user);
    }

    @Test
    @DisplayName("should throw user not found exception")
    void shouldThrowUserNotFoundException() {
        Mockito.when(this.userRepository.findById(ArgumentMatchers.anyLong()))
            .thenReturn(Mono.empty());

        StepVerifier.create(this.userFetchService.findById(1l))
            .expectError(UserNotFoundException.class)
            .verify();

        Mockito.verify(this.userRepository, Mockito.times(1)).findById(1l);
        Mockito.verify(this.userMapper, Mockito.times(0)).toUserResponse(ArgumentMatchers.any());
    }

}

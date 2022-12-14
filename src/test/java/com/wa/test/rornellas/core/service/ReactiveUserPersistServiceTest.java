package com.wa.test.rornellas.core.service;

import com.wa.test.rornellas.core.exception.UserNotFoundException;
import com.wa.test.rornellas.core.mapper.UserMapper;
import com.wa.test.rornellas.core.service.request.UserRequest;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class ReactiveUserPersistServiceTest {

    @MockBean
    ReactiveUserRepository userRepository;

    @MockBean
    UserMapper userMapper;

    ReactiveUserPersistService userPersistService;

    @BeforeEach
    void init() {
        this.userPersistService = new ReactiveUserPersistServiceImpl(this.userRepository, this.userMapper);
    }

    @Test
    @DisplayName("should create new user successfully")
    void shouldCreateNewUserSuccessfully() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        UserImpl user = Mockito.mock(UserImpl.class);
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        Mockito.when(this.userMapper.toUserEntity(userRequest))
                .thenReturn(user);
        Mockito.when(this.userRepository.save(user))
                .thenReturn(Mono.just(user));
        Mockito.when(this.userMapper.toUserResponse(user))
            .thenReturn(userResponse);

        StepVerifier.create(this.userPersistService.create(userRequest))
            .expectSubscription()
            .expectNext(userResponse)
            .verifyComplete();

        Mockito.verify(this.userMapper, Mockito.times(1)).toUserEntity(userRequest);
        Mockito.verify(this.userRepository, Mockito.times(1)).save(user);
        Mockito.verify(this.userMapper, Mockito.times(1)).toUserResponse(user);
    }

    @Test
    @DisplayName("should update user successfully")
    void shouldUpdateUserSuccessfully() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);

        UserImpl findUserById = Mockito.mock(UserImpl.class);
        UserImpl saveUser = Mockito.mock(UserImpl.class);

        UserResponse userResponse = Mockito.mock(UserResponse.class);

        Mockito.when(this.userMapper.toUserEntity(userRequest))
                .thenReturn(saveUser);

        Mockito.when(this.userRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.just(findUserById));

        Mockito.when(this.userRepository.save(saveUser))
                .thenReturn(Mono.just(saveUser));

        Mockito.when(this.userMapper.toUserResponse(saveUser))
            .thenReturn(userResponse);

        StepVerifier.create(this.userPersistService.update(1l, userRequest))
            .expectSubscription()
            .expectNext(userResponse)
            .verifyComplete();

        Mockito.verify(this.userMapper, Mockito.times(1)).toUserEntity(userRequest);
        Mockito.verify(this.userRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(this.userRepository, Mockito.times(1)).save(saveUser);
        Mockito.verify(this.userMapper, Mockito.times(1)).toUserResponse(saveUser);
    }

    @Test
    @DisplayName("should throw user not found exception when updating")
    void shouldThrowUserNotFoundExceptionWhenUpdating() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);

        UserImpl saveUser = Mockito.mock(UserImpl.class);

        Mockito.when(this.userMapper.toUserEntity(userRequest))
                .thenReturn(saveUser);

        Mockito.when(this.userRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.empty());

        StepVerifier.create(this.userPersistService.update(1l, userRequest))
            .expectError(UserNotFoundException.class)
            .verify();

        Mockito.verify(this.userMapper, Mockito.times(1)).toUserEntity(userRequest);
        Mockito.verify(this.userRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(this.userRepository, Mockito.times(0)).save(saveUser);
        Mockito.verify(this.userMapper, Mockito.times(0)).toUserResponse(saveUser);
    }

    @Test
    @DisplayName("should delete user successfully")
    void shouldDeleteUserSuccessfully() {
        UserImpl findUserById = Mockito.mock(UserImpl.class);

        Mockito.when(this.userRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.just(findUserById));

        Mockito.when(this.userRepository.deleteById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.empty());

        StepVerifier.create(this.userPersistService.delete(1l))
            .expectSubscription()
            .verifyComplete();

        Mockito.verify(this.userRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(this.userRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("should throw user not found exception when deleting")
    void shouldThrowUserNotFoundExceptionWhenDeleting() {
        Mockito.when(this.userRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.empty());

        StepVerifier.create(this.userPersistService.delete(1l))
            .expectSubscription()
            .expectError(UserNotFoundException.class)
            .verify();

        Mockito.verify(this.userRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(this.userRepository, Mockito.times(0)).deleteById(ArgumentMatchers.anyLong());
    }

}

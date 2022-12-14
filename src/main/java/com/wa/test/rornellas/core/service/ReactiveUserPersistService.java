package com.wa.test.rornellas.core.service;

import com.wa.test.rornellas.core.service.request.UserRequest;
import com.wa.test.rornellas.core.service.response.UserResponse;
import reactor.core.publisher.Mono;

public interface ReactiveUserPersistService {
    Mono<UserResponse> create(UserRequest userRequest);
    Mono<UserResponse> update(Long id, UserRequest userRequest);
    Mono<Void> delete(Long id);
}

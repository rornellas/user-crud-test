package com.wa.test.rornellas.core.service;

import com.wa.test.rornellas.core.service.response.UserResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveUserFetchService {
    Flux<UserResponse> list(Integer rows, Integer page);

    Mono<UserResponse> findById(Long id);
}

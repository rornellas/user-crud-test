package com.wa.test.rornellas.core.service;

import com.wa.test.rornellas.core.exception.UserNotFoundException;
import com.wa.test.rornellas.core.mapper.UserMapper;
import com.wa.test.rornellas.core.service.response.UserResponse;
import com.wa.test.rornellas.data.repository.ReactiveUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReactiveUserFetchServiceImpl implements ReactiveUserFetchService {

    private final ReactiveUserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public Flux<UserResponse> list(final Integer rows, final Integer page) {
        Pageable pageable = Pageable.ofSize(rows).withPage(page);
        return this.userRepository.findAllBy(pageable)
                .map(this.userMapper::toUserResponse)
                .doFirst(() -> log.info("Starting to fetch users with rows {} and page {}", rows, page))
                .doFinally(__ -> log.info("Finished fetching users with rows {} and page {}", rows, page));
    }

    @Override
    public Mono<UserResponse> findById(Long id) {
        return this.userRepository.findById(id)
                .switchIfEmpty(Mono.error(UserNotFoundException::new))
                .map(this.userMapper::toUserResponse)
                .doFirst(() -> log.info("Starting to fetch user with id {}", id))
                .doFinally(__ -> log.info("Finished fetching etch user with id {}", id));
    }
}

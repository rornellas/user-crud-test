package com.wa.test.rornellas.data.repository;

import com.wa.test.rornellas.data.entity.User;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveUserRepository {
    Flux<User> findAllBy(Pageable pageable);

    Mono<User> findById(Long id);
    Mono<Long> countById(Long id);
    Mono<User> save(User user);

    Mono<Void> deleteById(Long id);
}

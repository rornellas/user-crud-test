package com.wa.test.rornellas.data.repository;

import com.wa.test.rornellas.data.entity.UserImpl;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomReactiveUserRepository extends ReactiveUserRepository, ReactiveSortingRepository<UserImpl, String>, ReactiveCrudRepository<UserImpl, String> {
}

package com.wa.test.rornellas.api.controller;

import com.wa.test.rornellas.api.controller.router.UserRouter;
import com.wa.test.rornellas.core.exception.UserNotFoundException;
import com.wa.test.rornellas.core.service.ReactiveUserFetchService;
import com.wa.test.rornellas.core.service.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserFetchController {

    private final ReactiveUserFetchService reactiveUserFetchService;

    @GetMapping(UserRouter.LIST)
    public final Flux<UserResponse> list(
            @RequestParam(required = false, defaultValue = "10") final Integer rows,
            @RequestParam(required = false, defaultValue = "0") final Integer page
    ) {
        return this.reactiveUserFetchService.list(rows, page);
    }

    @GetMapping(UserRouter.FIND_BY_ID)
    public final Mono<UserResponse> findById(@PathVariable final Long id) {
        return this.reactiveUserFetchService.findById(id)
                .switchIfEmpty(Mono.error(UserNotFoundException::new));
    }

}

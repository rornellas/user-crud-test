package com.wa.test.rornellas.api.controller;

import com.wa.test.rornellas.api.controller.router.UserRouter;
import com.wa.test.rornellas.core.service.ReactiveUserPersistService;
import com.wa.test.rornellas.core.service.request.UserRequest;
import com.wa.test.rornellas.core.service.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserSaveController {

    private final ReactiveUserPersistService reactiveUserCreateService;

    @PostMapping(UserRouter.CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    public final Mono<UserResponse> create(@Valid @RequestBody final UserRequest userRequest) {
        return this.reactiveUserCreateService.create(userRequest);
    }

    @PutMapping(UserRouter.UPDATE)
    public final Mono<UserResponse> update(
        @PathVariable Long id,
        @Valid @RequestBody final UserRequest userRequest
    ) {
        return this.reactiveUserCreateService.update(id, userRequest);
    }

}

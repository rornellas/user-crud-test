package com.wa.test.rornellas.api.controller;

import com.wa.test.rornellas.api.controller.router.UserRouter;
import com.wa.test.rornellas.core.service.ReactiveUserPersistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserDeleteController {

    private final ReactiveUserPersistService reactiveUserCreateService;

    @DeleteMapping(UserRouter.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public final Mono<Void> delete(@PathVariable Long id) {
        return this.reactiveUserCreateService.delete(id);
    }

}

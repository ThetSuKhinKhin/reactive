package com.example.webfluxdemo1.controller;

import com.example.webfluxdemo1.dto.InputFailedValidationError;
import com.example.webfluxdemo1.dto.Response;
import com.example.webfluxdemo1.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive-math")
@RequiredArgsConstructor
public class ReactiveMathValidationController {

    private final ReactiveMathService reactiveMathService;

    @GetMapping("/square/{input}/throw")
    public Mono<Response> findSquare(@PathVariable int input) {
        if (input < 10 || input > 20) {
            throw  new InputFailedValidationError(input);
        }
        return reactiveMathService.findSquare(input);
    }

    @GetMapping("/square/{input}/mono-error")
    public Mono<Response> monoError(@PathVariable int input) {
        return Mono.just(input)
                .handle((integer, sink) -> { // sink = publisher
                    if (integer >= 10 && integer <= 20) {
                        sink.next(integer);
                    }
                    else {
                        sink.error(new InputFailedValidationError(integer));
                    }
                })
                .cast(Integer.class)
                .flatMap(i -> reactiveMathService.findSquare(i));
    }

    @GetMapping("/square/{input}/assignment")
    public Mono<ResponseEntity<Response>> assignment(
            @PathVariable int input) {
        return Mono.just(input)
                .filter(i -> i >= 10 && i <=20)
                .flatMap(i -> reactiveMathService.findSquare(i))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}

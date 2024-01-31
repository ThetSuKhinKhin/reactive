package com.example.webfluxdemo1.controller;

import com.example.webfluxdemo1.dto.MultiplyRequestDto;
import com.example.webfluxdemo1.dto.Response;
import com.example.webfluxdemo1.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/reactive-math")
@RequiredArgsConstructor
public class ReactiveMathController {

    private final ReactiveMathService reactiveMathService;

    @GetMapping(value = "/square/{input}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Response> findSquare(@PathVariable int input) {
        return reactiveMathService.findSquare(input);
    }

    //reactive type correctly / you should value and produce because data is release so use produce
    @GetMapping(value = "/table/{input}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTable(@PathVariable int input) {

        return reactiveMathService.multiplicaitonTable(input);
    }


    @PostMapping(value = "/multiply")
    public Mono<Response> multiply(
            @RequestBody Mono<MultiplyRequestDto> requestDtoMono,
    @RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        return reactiveMathService.multiply(requestDtoMono);
    }
}

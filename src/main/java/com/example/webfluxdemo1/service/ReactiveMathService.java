package com.example.webfluxdemo1.service;

import com.example.webfluxdemo1.dto.MultiplyRequestDto;
import com.example.webfluxdemo1.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input) {   // mono is publisher(one) = firstly emit data
        return Mono.fromSupplier(() -> input * input) //normally functional operation starts string and only one have terminal operation
                .map(v -> new Response(v)); // reactive operation => map is not terminal operation but that works

    }


    public Flux<Response> multiplicaitonTable(int input) {
        // this style is non-reactive
        /*List<Response> list = IntStream.rangeClosed(1, 10)  //1 to 10
                .peek(i -> SleepUtil.sleepSeconds(1)) //constumer is one input but no return type  // use for trace // mustn't use
                .mapToObj(i -> new Response(i * input))   // mapToObj(intFunction = input int and output int)
                .collect(Collectors.toList());
        return Flux.fromIterable(list);*/


        // reactive style
        return Flux.range(1, 10)   // flux.range is inclusive
                .delayElements(Duration.ofSeconds(1))
                //.doOnNext(i -> SleepUtil.sleepSeconds(1)) //doOnNext is run 1, run 2 to run 10
                .doOnNext(i -> System.out.println("Reactive-Math-Service Procession : " + i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequestDto> requesDtoMono) {
        return requesDtoMono
                .map(dto -> dto.getFirst() * dto.getSecond())
                //.map(Response::new) //they are the same
                .map(d -> new Response(d));
    }
}

package com.example.webfluxdemo1.service;

import com.example.webfluxdemo1.dto.Response;
import com.example.webfluxdemo1.util.SleepUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MathService {

    public Response findSquare(int input) {
        return new Response(input * input);
    }


    public List<Response> multiplicationTable(int input) {
        return IntStream.rangeClosed(1, 10)  //1 to 10
                .peek(i -> SleepUtil.sleepSeconds(1)) //constumer is one input but no return type  // use for trace // mustn't use
                .mapToObj(i -> new Response(i * input))   // mapToObj(intFunction = input int and output int)
                .collect(Collectors.toList());
    }
}

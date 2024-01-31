package com.example.webfluxdemo1.controller;

import com.example.webfluxdemo1.dto.Response;
import com.example.webfluxdemo1.service.MathService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/math")
@RequiredArgsConstructor
public class MathController {

    private final MathService mathService;

    @GetMapping("/square/{input}")  // use pathVariable so must use ui template{}
    public Response findSquare(@PathVariable int input) {
        return mathService.findSquare(input);
    }

    @GetMapping("/table/{input}")
    public List<Response> multiplicationTable(@PathVariable int input) {
        return mathService.multiplicationTable(input);
    }
}

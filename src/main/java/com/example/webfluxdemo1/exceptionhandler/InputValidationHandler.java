package com.example.webfluxdemo1.exceptionhandler;

import com.example.webfluxdemo1.dto.InputFailedValidationError;
import com.example.webfluxdemo1.dto.InputFailedValidationResponse;
import com.example.webfluxdemo1.dto.Response;
import com.example.webfluxdemo1.service.ReactiveMathService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@ControllerAdvice //watch
public class InputValidationHandler {



    @ExceptionHandler(InputFailedValidationError.class)
    public ResponseEntity<InputFailedValidationResponse>
    handleException(InputFailedValidationError ex) {
        InputFailedValidationResponse response =
                new InputFailedValidationResponse();
        response.setInput(ex.getInput());
        response.setErrorCode(ex.getErrorCode());
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }


}

package com.example.webfluxdemo1.util;

import java.util.stream.Stream;

public class Main {

    /*public static void main(String[] args) {
        Stream.of(1,2,3,4,5)
                .peek(i -> System.out.println("Stream : " + i))
                .filter(i -> i % 2 == 0)
                .peek(i -> System.out.println("Filter : " + i))
                .map(i -> i * i); // mustn't work because don't have terminal operation
    }*/

    // terminal operation = void
    // intermediate operation is only string
    // Functional Operation
    public static void main(String[] args) {
        Stream.of(1,2,3,4,5)
                .peek(i -> System.out.println("Stream : " + i))
                .filter(i -> i % 2 == 0)
                .peek(i -> System.out.println("Filter : " + i))
                .map(i -> i * i) // mustn't work because don't have terminal operation
                .peek(i -> System.out.println("Map : " + i))
                .limit(2)
                .forEach(System.out::println);

    }
}

package com.example.demo.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
public class TestResource {

    Counter visitCounter;

    public TestResource(MeterRegistry registry) {
        visitCounter = Counter.builder("visit_counter").description("Number of visits to the site").register(registry);
    }



    @GetMapping("/")
    public String hello()  {
        visitCounter.increment();
        return "Hello";
    }

}

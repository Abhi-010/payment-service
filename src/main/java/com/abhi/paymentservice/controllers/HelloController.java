package com.abhi.paymentservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("/hello")
@RestController
public class HelloController {

    @GetMapping("/")
    public String sayHello(){
        return "hello from Abhishek ";
    }
}

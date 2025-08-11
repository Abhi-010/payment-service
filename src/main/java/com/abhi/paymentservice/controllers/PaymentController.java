package com.abhi.paymentservice.controllers;

import com.abhi.paymentservice.services.PaymentServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentServices paymentServices;
    public PaymentController(PaymentServices paymentServices){
        this.paymentServices = paymentServices ;
    }

    @PostMapping("/{orderId}")
    public String initiatePayment(@PathVariable String orderId){
        return paymentServices.initiatePayment(orderId);
    }
}

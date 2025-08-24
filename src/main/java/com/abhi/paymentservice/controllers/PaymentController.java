package com.abhi.paymentservice.controllers;

import com.abhi.paymentservice.dtos.InitiatePaymentRequestDto;
import com.abhi.paymentservice.services.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentServices;
    public PaymentController(PaymentService paymentServices){
        this.paymentServices = paymentServices ;
    }

    @PostMapping
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) throws StripeException {
        return paymentServices.initiatePayment(requestDto.getOrderId(),requestDto.getEmail(),requestDto.getPhoneNumber(), requestDto.getAmount()) ;
    }
}

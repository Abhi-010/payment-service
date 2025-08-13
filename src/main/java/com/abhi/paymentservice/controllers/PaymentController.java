package com.abhi.paymentservice.controllers;

import com.abhi.paymentservice.dtos.InitiatePaymentRequestDto;
import com.abhi.paymentservice.services.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentServices;
    public PaymentController(PaymentService paymentServices){
        this.paymentServices = paymentServices ;
    }

    @PostMapping
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto paymentRequestDto) throws StripeException {

        paymentServices.initiatePayment("orderID",
                paymentRequestDto.getEmail(), paymentRequestDto.getPhoneNumber(), paymentRequestDto.getAmount());
        return null ;
    }
}

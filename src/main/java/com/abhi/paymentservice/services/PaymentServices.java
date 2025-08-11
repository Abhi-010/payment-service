package com.abhi.paymentservice.services;

import org.springframework.stereotype.Service;

@Service
public class PaymentServices {

    public String initiatePayment(String orderId){
        //Order order = orderService.getOrderDetails(orderId)
        // Long amount = order.getAmount();

        Long amount = 1010L ;

        return "hello";
    }
}

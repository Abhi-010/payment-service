package com.abhi.paymentservice.services.paymentgateways;

import org.springframework.stereotype.Component;

@Component
public class RazorpayPaymentGateway implements PaymentGateway{

    @Override
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) {
        return "";
    }
}

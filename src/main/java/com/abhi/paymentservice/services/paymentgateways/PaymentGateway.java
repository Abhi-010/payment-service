package com.abhi.paymentservice.services.paymentgateways;

public interface PaymentGateway {

    String generatePaymentLink();

    //boolean validatePayment() ;
}

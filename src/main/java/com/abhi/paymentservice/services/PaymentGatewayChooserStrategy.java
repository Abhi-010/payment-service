package com.abhi.paymentservice.services;

import com.abhi.paymentservice.services.paymentgateways.PaymentGateway;
import com.abhi.paymentservice.services.paymentgateways.RazorpayPaymentGateway;
import com.abhi.paymentservice.services.paymentgateways.StripePaymentGateway;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentGatewayChooserStrategy {

    RazorpayPaymentGateway razorpayPaymentGateway;
    StripePaymentGateway stripePaymentGateway;
    public PaymentGatewayChooserStrategy(RazorpayPaymentGateway razorpayPaymentGateway , StripePaymentGateway stripePaymentGateway){
        this.razorpayPaymentGateway = razorpayPaymentGateway ;
        this.stripePaymentGateway = stripePaymentGateway ;
    }
    public PaymentGateway getBestPaymentGateway(){

        // business logic to select best payment gateway

//        int randomInt = new Random().nextInt() ;
//        if(randomInt%2 == 0){
//            return razorpayPaymentGateway;
//        }
        return stripePaymentGateway ;
    }
}

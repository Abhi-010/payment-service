package com.abhi.paymentservice.services;

import com.abhi.paymentservice.services.paymentgateways.PaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy ;

    public PaymentService(PaymentGatewayChooserStrategy paymentGatewayChooserStrategy){
        this.paymentGatewayChooserStrategy = paymentGatewayChooserStrategy ;
    }

    public String initiatePayment(String orderId,String email, String phoneNumber, Long amount) throws StripeException {
        //Order order = orderService.getOrderDetails(orderId)
        // Long amount = order.getAmount();

        PaymentGateway paymentGateway = paymentGatewayChooserStrategy.getBestPaymentGateway();

        paymentGateway.generatePaymentLink(orderId,email,phoneNumber,amount);

        return "hello";
    }
}

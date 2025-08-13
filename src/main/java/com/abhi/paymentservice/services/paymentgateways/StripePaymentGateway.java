package com.abhi.paymentservice.services.paymentgateways;


import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.param.PaymentLinkCreateParams;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway{

    PaymentLinkCreateParams paymentLinkCreateParams ;

    public StripePaymentGateway(PaymentLinkCreateParams paymentLinkCreateParams){
        this.paymentLinkCreateParams = paymentLinkCreateParams ;
    }

    @Override
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) throws StripeException {

        PaymentLinkCreateParams params = PaymentLinkCreateParams.builder()
                .addLineItem(
                        PaymentLinkCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        PaymentLinkCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("inr")
                                                .setUnitAmount(amount) // amount in paise
                                                .setProductData(
                                                        PaymentLinkCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Order #" + orderId)
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
              // .setCustomerEmail(email) // email of buyer
                .build();

        PaymentLink paymentLink = PaymentLink.create(params);

        return paymentLink.getUrl() ;
    }
}

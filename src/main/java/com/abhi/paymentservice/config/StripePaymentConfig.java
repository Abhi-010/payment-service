package com.abhi.paymentservice.config;

import com.stripe.Stripe;
import com.stripe.param.PaymentLinkCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripePaymentConfig {

    @Bean
    public PaymentLinkCreateParams createPaymentLinkParam(){
        return PaymentLinkCreateParams.builder().build();
    }

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }
}

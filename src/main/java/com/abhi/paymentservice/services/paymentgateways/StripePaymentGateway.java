package com.abhi.paymentservice.services.paymentgateways;


import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.net.RequestOptions;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Component;
import com.stripe.model.checkout.Session;


@Component
public class StripePaymentGateway implements PaymentGateway{

    PaymentLinkCreateParams paymentLinkCreateParams ;

    public StripePaymentGateway(PaymentLinkCreateParams paymentLinkCreateParams){
        this.paymentLinkCreateParams = paymentLinkCreateParams ;
    }

    @Override
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) throws StripeException {


        CustomerCreateParams customerCreateParams = CustomerCreateParams.builder()
                .setEmail(email)
                .setName("Abhishek Kumar")
                //.setAddress(EmptyParam.valueOf("Bangalore India"))
                .build();

        Customer customer = Customer.create(customerCreateParams);




//        PaymentLinkCreateParams params = PaymentLinkCreateParams.builder()
//                .addLineItem(
//                        PaymentLinkCreateParams.LineItem.builder()
//                                .setQuantity(1L)
//                                .setPriceData(
//                                        PaymentLinkCreateParams.LineItem.PriceData.builder()
//                                                .setCurrency("inr")
//                                                .setUnitAmount(amount) // amount in paise
//                                                .setProductData(
//                                                        PaymentLinkCreateParams.LineItem.PriceData.ProductData.builder()
//                                                                .setName("Order #" + orderId)
//                                                                .build()
//                                                )
//                                                .build()
//                                )
//                                .build()
//
//                )
//                .setCustomerCreation(PaymentLinkCreateParams.CustomerCreation.ALWAYS)
//               // .setCustomerEmail() // already set
//                .setBillingAddressCollection(PaymentLinkCreateParams.BillingAddressCollection.REQUIRED)
//              // .setCustomerEmail(email) // email of buyer
//                .setAfterCompletion(
//                        PaymentLinkCreateParams.AfterCompletion.builder()
//                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT) //
//                                .setRedirect(
//                                        PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
//                                                .setUrl("https://scaler.com?payment_id={CHECKOUT_SESSION_ID}") // where user should go after payment
//                                                .build()
//                                )
//                                .build()
//                )
//                .setCustomerCreation(PaymentLinkCreateParams.CustomerCreation.ALWAYS)
//                .build();

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("https://scaler.com?payment_id={CHECKOUT_SESSION_ID}")
                        .setCancelUrl("https://myapp.com/payment/cancel?order_id=\" + orderId")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency("inr")
                                                        .setUnitAmount(amount)
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                        .setName("Order #" + orderId)
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                        .build()
                        )
                        .putMetadata("order_id", orderId) // track order in Stripe
                        .setBillingAddressCollection(SessionCreateParams.BillingAddressCollection.REQUIRED)
                        .build();

        RequestOptions options =
                RequestOptions.builder()
                        .setIdempotencyKey(orderId)
                        .build();
       // PaymentLink paymentLink = PaymentLink.create(params,options);

        Session session = Session.create(params,options);
        System.out.println("you are here..");
        System.out.println(session.toJson());
        return session.getPaymentLink() ;
    }
}

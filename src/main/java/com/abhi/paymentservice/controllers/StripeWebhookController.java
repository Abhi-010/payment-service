package com.abhi.paymentservice.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.WebhookEndpoint;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/webhook")
public class StripeWebhookController {

    private static final String STRIPE_ENDPOINT_SECRET = "whsec_wdd3drzPclKlwlPKI0Cue01gczEtErrq"; // from Stripe Dashboard

    // whsec_wdd3drzPclKlwlPKI0Cue01gczEtErrq

    @PostMapping
    public String handleStripeEvent(HttpServletRequest request, @RequestBody String payload,
                                    @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event = null;

        try {
            // Verify signature
            event = Webhook.constructEvent(
                    payload, sigHeader, STRIPE_ENDPOINT_SECRET
            );
            System.out.println("You are in webhook controller.. ");
            // Handle event types
            switch (event.getType()) {
                case "payment_intent.succeeded":
                    System.out.println("✅ Payment succeeded: " + event.getDataObjectDeserializer().getRawJson());
                    // TODO: Update order/payment status in DB → SUCCESS
                    break;

                case "payment_intent.payment_failed":
                    //System.out.println("❌ Payment failed: " + event.getDataObjectDeserializer().getObject().get().getId());
                    // TODO: Update order/payment status in DB → FAILED
                    break;

                case "checkout.session.completed":
                    //System.out.println("🛒 Checkout completed: " + event.getDataObjectDeserializer().getObject().get().getId());
                    // TODO: Map checkout session → order, mark as PAID
                    break;

                default:
                    System.out.println("Unhandled event type: " + event.getType());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        return ""; // Stripe expects 200 OK empty response
    }

    @GetMapping("/{endpoint}")
    public String deleteWebhookEndpoint(@PathVariable String endpoint) throws StripeException {

        System.out.println("delete Webhook....");
        WebhookEndpoint resource = WebhookEndpoint.retrieve(endpoint) ;

        WebhookEndpoint webhookEndpoint = resource.delete();

        return "successfull" ;

    }
}

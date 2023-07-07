package com.mariobank.payment.rest;

import com.mariobank.payment.messaging.producer.PaymentTransferResource;
import com.mariobank.payment.model.PaymentRequest;
import com.mariobank.payment.model.PaymentStatus;
import com.mariobank.payment.repository.PaymentStatusRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/payment")
public class PaymentController {

    @Inject
    PaymentTransferResource paymentTransferResource;

    @POST
    @Path("/transfer")
    @Produces(MediaType.TEXT_PLAIN)
    public String processTransfer(PaymentRequest paymentRequest) {
        return paymentTransferResource.process(paymentRequest);
    }

    @GET
    @Path("/status/{transactionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PaymentStatus getPaymentStatus(@PathParam("transactionId") String transactionId)
    {
        return PaymentStatusRepository.getInstance().get(transactionId);
    }
}

package com.mariobank.payment.messaging.producer;

import com.mariobank.payment.messaging.model.PaymentTransferMessage;
import com.mariobank.payment.model.PaymentRequest;
import jakarta.ws.rs.BadRequestException;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.UUID;

public class PaymentTransferResource {

    @Channel("c2c-queue")
    Emitter<PaymentTransferMessage> c2cRequestEmitter;

    @Channel("b2c-queue")
    Emitter<PaymentTransferMessage> b2cRequestEmitter;

    public String process(PaymentRequest paymentRequest)
    {
        UUID uuid = UUID.randomUUID();
        //send message to queue
        PaymentTransferMessage paymentTransferMessage = new PaymentTransferMessage(paymentRequest);
        paymentTransferMessage.setTransactionId(uuid.toString());
        if ("C2C".equals(paymentRequest.getPaymentType())) {
            c2cRequestEmitter.send(paymentTransferMessage);
        } else if ("B2C".equals(paymentRequest.getPaymentType())) {
            b2cRequestEmitter.send(paymentTransferMessage);
        } else {
            throw new BadRequestException("Invalid payment type");
        }
        return uuid.toString();
    }
}

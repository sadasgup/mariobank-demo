package com.mariobank.payment.messaging.producer;

import com.mariobank.payment.constants.PaymentType;
import com.mariobank.payment.messaging.model.PaymentTransferMessage;
import com.mariobank.payment.model.PaymentRequest;
import io.quarkus.runtime.util.StringUtil;
import jakarta.ws.rs.BadRequestException;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.UUID;

import static com.mariobank.payment.constants.PaymentTransferConstants.*;

public class PaymentTransferResource {

    @Channel("c2c-queue")
    Emitter<PaymentTransferMessage> c2cRequestEmitter;

    @Channel("b2c-queue")
    Emitter<PaymentTransferMessage> b2cRequestEmitter;

    public String process(PaymentRequest paymentRequest) {
        String transactionId =
                StringUtil.isNullOrEmpty(paymentRequest.getTransactionId()) ?
                                UUID.randomUUID().toString() : paymentRequest.getTransactionId();
        //send message to kafka topic
        PaymentTransferMessage paymentTransferMessage = new PaymentTransferMessage(paymentRequest);
        paymentTransferMessage.setTransactionId(PAYMENT_TRANSFER_TRANSACTION_ID_PREFIX + transactionId);
        if (PaymentType.C2C.toString().equals(paymentRequest.getPaymentType())) {
            c2cRequestEmitter.send(paymentTransferMessage);
        } else if (PaymentType.B2C.toString().equals(paymentRequest.getPaymentType())) {
            b2cRequestEmitter.send(paymentTransferMessage);
        } else {
            throw new BadRequestException("Invalid payment type");
        }
        return transactionId;
    }
}

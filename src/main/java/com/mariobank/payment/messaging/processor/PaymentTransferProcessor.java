package com.mariobank.payment.messaging.processor;

import com.mariobank.payment.constants.PaymentType;
import com.mariobank.payment.messaging.model.PaymentTransferMessage;
import com.mariobank.payment.model.PaymentStatus;
import com.mariobank.payment.repository.PaymentStatusRepository;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import static com.mariobank.payment.constants.PaymentTransferConstants.C2C_PAYMENT_DAILY_LIMIT;
import static com.mariobank.payment.constants.PaymentTransferConstants.PAYMENT_FAIL;

public class PaymentTransferProcessor {

    @Incoming("c2cpayments")
    @Blocking
    public void processc2c(PaymentTransferMessage paymentTransferMessage)
    {
        createPaymentStatus(paymentTransferMessage);
    }

    @Incoming("b2cpayments")
    @Blocking
    public void processb2c(PaymentTransferMessage paymentTransferMessage)
    {
        createPaymentStatus(paymentTransferMessage);
    }

    private void createPaymentStatus(PaymentTransferMessage paymentTransferMessage)
    {
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setAmount(paymentTransferMessage.getAmount());
        paymentStatus.setTransactionId(paymentTransferMessage.getTransactionId());
        paymentStatus.setPaymentType(PaymentType.valueOf(paymentTransferMessage.getPaymentType()).label);
        paymentStatus.setTransactionStatus(paymentTransferMessage.getPaymentTransferStatus());
        if (paymentStatus.getTransactionStatus().equals(PAYMENT_FAIL))
        {
            paymentStatus.setFailureReason("Transfer amount "+paymentTransferMessage.getAmount()+" exceeds daily transfer limit of "+C2C_PAYMENT_DAILY_LIMIT);
        }
        //persist
        PaymentStatusRepository.getInstance().add(paymentStatus);
    }
}

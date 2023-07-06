package com.mariobank.payment.messaging.processor;

import com.mariobank.payment.messaging.model.PaymentTransferMessage;
import com.mariobank.payment.model.PaymentStatus;
import com.mariobank.payment.repository.PaymentStatusRepository;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.Random;

public class PaymentTransferProcessor {
    private Random random = new Random();

    @Incoming("c2cpayments")
    @Blocking
    public void processc2c(PaymentTransferMessage paymentTransferMessage)
    {
        createPaymentStatus(paymentTransferMessage, "Customer To Customer");
    }

    @Incoming("b2cpayments")
    @Blocking
    public void processb2c(PaymentTransferMessage paymentTransferMessage)
    {
        createPaymentStatus(paymentTransferMessage, "Business To Customer");
    }

    private void createPaymentStatus(PaymentTransferMessage paymentTransferMessage, String paymentType)
    {
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setAmount(paymentTransferMessage.getAmount());
        paymentStatus.setTransactionId(paymentTransferMessage.getTransactionId());
        paymentStatus.setPaymentType(paymentType);
        int someRandomInt = random.nextInt(1000);
        if (someRandomInt%5 == 0)
        {
            paymentStatus.setTransactionStatus("FAIL");
        }
        PaymentStatusRepository.getInstance().add(paymentStatus);
    }
}

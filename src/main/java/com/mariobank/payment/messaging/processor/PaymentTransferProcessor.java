package com.mariobank.payment.messaging.processor;

import com.mariobank.payment.constants.PaymentType;
import com.mariobank.payment.messaging.model.PaymentTransferMessage;
import com.mariobank.payment.model.PaymentStatus;
import com.mariobank.payment.repository.PaymentStatusRepository;
import io.quarkus.runtime.util.StringUtil;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import static com.mariobank.payment.constants.PaymentTransferConstants.*;

public class PaymentTransferProcessor {

    @Incoming("c2cpayments")
    public void processc2c(PaymentTransferMessage paymentTransferMessage)
    {
        createPaymentStatus(paymentTransferMessage);
    }

    @Incoming("b2cpayments")
    public void processb2c(PaymentTransferMessage paymentTransferMessage)
    {
        createPaymentStatus(paymentTransferMessage);
    }

    private void createPaymentStatus(PaymentTransferMessage paymentTransferMessage) {
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setSourceAccount(paymentTransferMessage.getSourceAccount());
        paymentStatus.setDestinationAccount(paymentTransferMessage.getDestinationAccount());
        paymentStatus.setAmount(paymentTransferMessage.getAmount());
        paymentStatus.setTransactionId(paymentTransferMessage.getTransactionId());
        paymentStatus.setPaymentType(PaymentType.valueOf(paymentTransferMessage.getPaymentType()).label);
        paymentStatus.
                setTransactionStatus(isLegit(paymentTransferMessage) ?
                        PAYMENT_TRANSFER_SUCCESS : PAYMENT_TRANSFER_FAILURE);
        if (paymentStatus.getTransactionStatus().equals(PAYMENT_TRANSFER_FAILURE)) {
            paymentStatus.setFailureReason("Fraud payment attempted");
        }
        //persist
        PaymentStatusRepository.getInstance().add(paymentStatus);
    }

    private boolean isLegit(PaymentTransferMessage paymentTransferMessage) {
        String transactionId = paymentTransferMessage.getTransactionId();
        return !StringUtil.isNullOrEmpty(transactionId)
                        && transactionId.startsWith(PAYMENT_TRANSFER_TRANSACTION_ID_PREFIX);
    }
}

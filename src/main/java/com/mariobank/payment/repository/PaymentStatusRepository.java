package com.mariobank.payment.repository;

import com.mariobank.payment.model.PaymentStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PaymentStatusRepository {

    private Map<String, PaymentStatus> paymentStatusMap;

    private PaymentStatusRepository()
    {
        paymentStatusMap = new HashMap<>();
    }

    private static final PaymentStatusRepository instance = new PaymentStatusRepository();

    public static PaymentStatusRepository getInstance()
    {
        return instance;
    }

    public PaymentStatus get(String transactionId)
    {
        PaymentStatus paymentStatus = paymentStatusMap.get(transactionId);
        if (Objects.isNull(paymentStatus))
        {
            paymentStatus = new PaymentStatus();
            paymentStatus.setTransactionId(transactionId);
            paymentStatus.setTransactionStatus("Invalid transaction id");
        }
        return paymentStatus;
    }

    public void add (PaymentStatus paymentStatus)
    {
        paymentStatusMap.put(paymentStatus.getTransactionId(), paymentStatus);
    }

}

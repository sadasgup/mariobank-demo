package com.mariobank.payment.messaging.model;

import com.mariobank.payment.model.PaymentRequest;

public class PaymentTransferMessage {

    private String paymentType;

    private Double amount;

    private String transactionId;

    private String paymentTransferStatus;


    public PaymentTransferMessage()
    {
        //do nothing
    }

    public PaymentTransferMessage(PaymentRequest paymentRequest)
    {
        this.paymentType = paymentRequest.getPaymentType();
        this.amount = paymentRequest.getAmount();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentTransferStatus() {
        return paymentTransferStatus;
    }

    public void setPaymentTransferStatus(String paymentTransferStatus) {
        this.paymentTransferStatus = paymentTransferStatus;
    }
}

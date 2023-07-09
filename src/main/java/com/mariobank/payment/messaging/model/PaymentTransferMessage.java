package com.mariobank.payment.messaging.model;

import com.mariobank.payment.model.PaymentRequest;
import io.quarkus.runtime.util.StringUtil;

public class PaymentTransferMessage {

    private String paymentType;

    private Double amount;

    private String transactionId;

    private Integer sourceAccount;

    private Integer destinationAccount;

    public PaymentTransferMessage()
    {
        //do nothing
    }

    public PaymentTransferMessage(PaymentRequest paymentRequest)
    {
        this.paymentType = paymentRequest.getPaymentType();
        this.amount = paymentRequest.getAmount();
        this.transactionId = paymentRequest.getTransactionId();
        this.sourceAccount = paymentRequest.getSourceAccount();
        this.destinationAccount = paymentRequest.getDestinationAccount();
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
        if (StringUtil.isNullOrEmpty(this.transactionId)) {
            this.transactionId = transactionId;
        }
    }

    public Integer getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Integer sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Integer getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Integer destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}

package com.mariobank.payment.model;

import io.quarkus.runtime.util.StringUtil;

public class PaymentRequest {

    private String paymentType;

    private Double amount;

    private Integer sourceAccount;

    private Integer destinationAccount;

    private String transactionId;

    public String getPaymentType() {
        return StringUtil.isNullOrEmpty(paymentType) ? "C2C" : paymentType;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}

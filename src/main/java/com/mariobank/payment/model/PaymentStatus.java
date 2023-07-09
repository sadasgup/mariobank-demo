package com.mariobank.payment.model;

import com.mariobank.payment.constants.PaymentTransferConstants;

import static com.mariobank.payment.constants.PaymentTransferConstants.PAYMENT_TRANSFER_SUCCESS;
import static com.mariobank.payment.constants.PaymentTransferConstants.PAYMENT_TRANSFER_TRANSACTION_ID_PREFIX;

public class PaymentStatus {

    private String paymentType;

    private Integer sourceAccount;

    private Integer destinationAccount;

    private Double amount;

    private String transactionStatus;

    private String transactionId;

    private String failureReason = "N/A";

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionId() {
        if (transactionId.startsWith(PAYMENT_TRANSFER_TRANSACTION_ID_PREFIX))
        {
            transactionId = transactionId.split(PAYMENT_TRANSFER_TRANSACTION_ID_PREFIX,2)[1];
        }
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

}

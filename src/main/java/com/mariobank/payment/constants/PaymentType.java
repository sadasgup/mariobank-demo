package com.mariobank.payment.constants;

public enum PaymentType {
    C2C ("Customer to Customer"),
    B2C ("Business to Customer");

    public final String label;

    private PaymentType(String label)
    {
        this.label = label;
    }
}

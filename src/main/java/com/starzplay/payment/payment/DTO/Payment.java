package com.starzplay.payment.payment.DTO;

import com.starzplay.payment.payment.Model.PaymentMethod;

import java.util.List;

/**
 * Created by Khuram on 3/13/2020.
 */
public class Payment {

    private List<PaymentMethod> paymentMethods;

    public Payment() {
    }

    public Payment(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}

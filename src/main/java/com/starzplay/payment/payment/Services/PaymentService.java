package com.starzplay.payment.payment.Services;

import com.starzplay.payment.payment.DTO.Payment;

/**
 * Created by Khuram on 3/14/2020.
 */

public interface PaymentService {

    /**
     * Used to insert new payment
     *
     * @param listOfPayment
     */
    void savePayment(Payment listOfPayment);

    /**
     * Used to update payment method
     *
     * @param payment List of PaymentMethod
     */
    void updatePayment(Payment payment, Long id);

    /**
     * Used to get All payment methods
     *
     * @return
     */
    Payment getPaymentMethods();

    /**
     * Used to get Payment method by plan id
     *
     * @param planId
     * @return
     */
    Payment getPaymentById(Long planId);

    /**
     * Used to get payment w.r.t net amount
     *
     * @param netAmount
     * @return
     */
    Payment getPaymentByNetAmount(Float netAmount);

    /**
     * Used to get payment w.r.t gross amount
     *
     * @param grossAmount
     * @return
     */
    Payment getPaymentByGrossAmount(Float grossAmount);

    /**
     * Used to get payment w.r.t tax amount
     *
     * @param taxAmount
     * @return
     */
    Payment getPaymentByTaxAmount(Float taxAmount);

    /**
     * Used to get payment w.r.t currency
     *
     * @param currency
     * @return
     */
    Payment getPaymentByCurrency(String currency);

    /**
     * Used to get payment w.r.t duration
     *
     * @param duration
     * @return
     */
    Payment getPaymentByDuration(String duration);

    /**
     * Used to get payment by payment-method name
     *
     * @param name
     * @return
     */
    Payment getPaymentByName(String name);

    /**
     * Used to get payment by payment-method displayName
     *
     * @param displayName
     * @return
     */
    Payment getPaymentByDisplayName(String displayName);

    /**
     * Used to get payment by payment Type
     *
     * @param paymentType
     * @return
     */
    Payment getPaymentByPaymentType(String paymentType);


}

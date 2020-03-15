package com.starzplay.payment.payment.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Khuram on 3/13/2020.
 */
@Entity
public class PaymentPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float netAmount;
    private Float taxAmount;
    private Float grossAmount;
    private String currency;
    private String duration;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE,
                            CascadeType.DETACH,CascadeType.PERSIST})
    @JoinColumn(name = "payment_id")
//    @JsonIgnoreProperties("paymentMethod")
    private PaymentMethod paymentMethod;

    public PaymentPlan() {
    }

    public PaymentPlan(Float netAmount, Float taxAmount, Float grossAmount, String currency, String duration, PaymentMethod paymentMethod) {
        this.netAmount = netAmount;
        this.taxAmount = taxAmount;
        this.grossAmount = grossAmount;
        this.currency = currency;
        this.duration = duration;
        this.paymentMethod = paymentMethod;
    }


    public PaymentPlan(Long id,Float netAmount, Float taxAmount, Float grossAmount, String currency, String duration) {
        this.netAmount = netAmount;
        this.taxAmount = taxAmount;
        this.grossAmount = grossAmount;
        this.currency = currency;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Float netAmount) {
        this.netAmount = netAmount;
    }

    public Float getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Float taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Float getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Float grossAmount) {
        this.grossAmount = grossAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

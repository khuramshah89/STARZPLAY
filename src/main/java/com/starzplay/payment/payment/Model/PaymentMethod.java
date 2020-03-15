package com.starzplay.payment.payment.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khuram on 3/13/2020.
 */
@Entity
public class PaymentMethod implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long paymentId;

    @NotNull
    private String name;
    private String  displayName;
    private String paymentType;
    @OneToMany(mappedBy ="paymentMethod" ,cascade = {CascadeType.REFRESH,CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.PERSIST})
    @JsonIgnoreProperties("paymentMethod")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PaymentPlan> paymentPlans=new ArrayList<>();

    public PaymentMethod() {
    }

    public PaymentMethod(String name, String displayName, String paymentType, List<PaymentPlan> paymentPlans) {
        this.name = name;
        this.displayName = displayName;
        this.paymentType = paymentType;
        this.paymentPlans = paymentPlans;
    }

    public PaymentMethod(@NotNull String name, String displayName, String paymentType) {
        this.name = name;
        this.displayName = displayName;
        this.paymentType = paymentType;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public List<PaymentPlan> getPaymentPlans() {
        return paymentPlans;
    }

    public void setPaymentPlans(List<PaymentPlan> paymentPlans) {
        this.paymentPlans = paymentPlans;
    }
}

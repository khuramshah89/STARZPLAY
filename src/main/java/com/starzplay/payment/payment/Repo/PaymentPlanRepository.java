package com.starzplay.payment.payment.Repo;

import com.starzplay.payment.payment.Model.PaymentPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Khuram on 3/13/2020.
 */
@Repository
public interface PaymentPlanRepository extends CrudRepository<PaymentPlan, Long> {

    List<PaymentPlan> findByNetAmount(Float netAmount);

    List<PaymentPlan> findByGrossAmount(Float grossAmount);

    List<PaymentPlan> findByTaxAmount(Float taxAmount);

    List<PaymentPlan> findByCurrency(String currency);

    List<PaymentPlan> findByDuration(String duration);
}

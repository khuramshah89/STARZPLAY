package com.starzplay.payment.payment.Services;

import com.starzplay.payment.payment.DTO.Payment;
import com.starzplay.payment.payment.Exception.RecordNotFound;
import com.starzplay.payment.payment.Model.PaymentMethod;
import com.starzplay.payment.payment.Model.PaymentPlan;
import com.starzplay.payment.payment.Repo.PaymentMethodRepository;
import com.starzplay.payment.payment.Repo.PaymentPlanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Khuram on 3/14/2020.
 */
@Service
public class PaymentServiceImp implements PaymentService {

    Logger logger = LoggerFactory.getLogger(PaymentServiceImp.class);

    @Autowired
    PaymentMethodRepository methodRepository;

    @Autowired
    PaymentPlanRepository planRepository;


    @Override
    public void savePayment(Payment paymentList) {
        try {
            paymentList.getPaymentMethods().forEach(method -> {
                PaymentMethod paymentMethod = new PaymentMethod(method.getName(), method.getDisplayName(), method.getPaymentType());
                methodRepository.save(paymentMethod);
                method.getPaymentPlans().forEach(plan -> {
                    PaymentPlan paymentPlan = new PaymentPlan(plan.getNetAmount(), plan.getTaxAmount(), plan.getGrossAmount(), plan.getCurrency(), plan.getDuration(), paymentMethod);
                    planRepository.save(paymentPlan);
                });
            });
            logger.info("Payment method is successfully saved.");
        } catch (RuntimeException e) {
            throw new RuntimeException("Oops ! something went wrong while inserting record ");
        }
    }

    @Override
    public void updatePayment(Payment payment, Long id) {

        if (!payment.getPaymentMethods().isEmpty()) {
            try {
                PaymentMethod method = payment.getPaymentMethods().get(0);
                PaymentPlan transientPlan = method.getPaymentPlans().get(0);
                planRepository.findById(id).map(paymentPlan -> {
                    paymentPlan.setNetAmount(transientPlan.getNetAmount());
                    paymentPlan.setTaxAmount(transientPlan.getTaxAmount());
                    paymentPlan.setGrossAmount(transientPlan.getGrossAmount());
                    paymentPlan.setDuration(transientPlan.getDuration());
                    paymentPlan.setCurrency(transientPlan.getCurrency());
                    methodRepository.findById(paymentPlan.getPaymentMethod().getPaymentId()).map(paymentMethod -> {
                        paymentMethod.setName(method.getName());
                        paymentMethod.setDisplayName(method.getDisplayName());
                        paymentMethod.setPaymentType(method.getPaymentType());
                        return methodRepository.save(paymentMethod);
                    }).orElseThrow(() -> new RecordNotFound("Payment method not found for plan: " + id));
                    return planRepository.save(paymentPlan);
                }).orElseThrow(RecordNotFound::new);
            } catch (RuntimeException re) {
                if (re.getMessage() != null) {
                    logger.error(re.getMessage());
                    throw new RuntimeException(re.getMessage());
                }
                logger.error("Record not updated for plan id: " + id);
                throw new RuntimeException("Oops ! something went wrong");
            }
            logger.info("Payment Method updated for plan Id: " + id);
        } else {
            logger.error("Payment method is empty");
        }
    }

    @Override
    public Payment getPaymentMethods() {
        return new Payment((List<PaymentMethod>) methodRepository.findAll());
    }

    @Override
    public Payment getPaymentByName(String name) {
        return new Payment(methodRepository.findByName(name));
    }

    @Override
    public Payment getPaymentByDisplayName(String displayName) {
        return new Payment(methodRepository.findByDisplayName(displayName));
    }

    @Override
    public Payment getPaymentByPaymentType(String paymentType) {
        return new Payment(methodRepository.findByPaymentType(paymentType));
    }

    @Override
    public Payment getPaymentById(Long planId) {
        List<PaymentMethod> methods = new ArrayList<>();
        Optional<PaymentPlan> plan = planRepository.findById(planId);
        plan.ifPresent(paymentPlan -> {
            methods.add(new PaymentMethod(paymentPlan.getPaymentMethod().getName(), paymentPlan.getPaymentMethod().getDisplayName(), paymentPlan.getPaymentMethod().getPaymentType(), Arrays.asList(paymentPlan)));
        });
        return new Payment(new ArrayList<PaymentMethod>(methods));
    }


    @Override
    public Payment getPaymentByGrossAmount(Float grossAmount) {
        return paymentMapper(planRepository.findByGrossAmount(grossAmount));
    }

    @Override
    public Payment getPaymentByNetAmount(Float netAmount) {
        return paymentMapper(planRepository.findByNetAmount(netAmount));
    }

    @Override
    public Payment getPaymentByTaxAmount(Float taxAmount) {
        return paymentMapper(planRepository.findByTaxAmount(taxAmount));
    }

    @Override
    public Payment getPaymentByCurrency(String currency) {
        return paymentMapper(planRepository.findByCurrency(currency));
    }

    @Override
    public Payment getPaymentByDuration(String duration) {
        return paymentMapper(planRepository.findByDuration(duration));
    }

    private Payment paymentMapper(List<PaymentPlan> plans) {
        List<PaymentMethod> methods = new ArrayList<>();
        plans.stream().filter(paymentPlan -> paymentPlan.getPaymentMethod() != null).forEach(paymentPlan -> {
            methods.add(new PaymentMethod(paymentPlan.getPaymentMethod().getName(), paymentPlan.getPaymentMethod().getDisplayName(), paymentPlan.getPaymentMethod().getPaymentType(), new ArrayList<PaymentPlan>(plans)));
        });
        return new Payment(new ArrayList<PaymentMethod>(methods));
    }
}

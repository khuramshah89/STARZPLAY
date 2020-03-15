package com.starzplay.payment.payment.Repo;

import com.starzplay.payment.payment.Model.PaymentMethod;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Khuram on 3/13/2020.
 */
@Repository
public interface PaymentMethodRepository extends CrudRepository<PaymentMethod,Long> {

    List<PaymentMethod> findByName(String name);
    List<PaymentMethod> findByDisplayName(String displayName);
    List<PaymentMethod> findByPaymentType(String paymentType);
}

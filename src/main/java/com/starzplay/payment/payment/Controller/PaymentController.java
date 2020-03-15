package com.starzplay.payment.payment.Controller;

import com.starzplay.payment.payment.DTO.Payment;
import com.starzplay.payment.payment.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Khuram on 3/13/2020.
 */
@RestController
public class PaymentController {


    @Autowired
    PaymentService paymentService;

    @GetMapping("/payment-methods")
    public ResponseEntity getAll(@RequestParam(value = "id", required = false) Long planId, @RequestParam (required = false) String name,
                                 @RequestParam (required = false) String displayName,
                                 @RequestParam (required = false) String paymentType,
                                 @RequestParam(required = false) Float taxAmount,
                                 @RequestParam(required = false) Float grossAmount,
                                 @RequestParam(required = false) Float netAmount,
                                 @RequestParam(required = false) String currency,
                                 @RequestParam(required = false) String duration
                                 ) {
        if (planId != null) {
            return new ResponseEntity<>(paymentService.getPaymentById(planId), HttpStatus.OK);
        } else if (name != null) {
            return new ResponseEntity<>(paymentService.getPaymentByName(name), HttpStatus.OK);
        }else if(displayName !=null){
            return new ResponseEntity<>(paymentService.getPaymentByDisplayName(displayName),HttpStatus.OK);
        }else if(paymentType!=null){
            return new ResponseEntity<>(paymentService.getPaymentByPaymentType(paymentType),HttpStatus.OK);
        }else if(taxAmount!=null){
            return new ResponseEntity<>(paymentService.getPaymentByTaxAmount(taxAmount),HttpStatus.OK);
        }else if(grossAmount!=null){
            return new ResponseEntity<>(paymentService.getPaymentByGrossAmount(grossAmount),HttpStatus.OK);
        }else if(netAmount !=null){
            return new ResponseEntity<>(paymentService.getPaymentByNetAmount(netAmount),HttpStatus.OK);
        }else if(currency!=null){
            return new ResponseEntity<>(paymentService.getPaymentByCurrency(currency),HttpStatus.OK);
        }else if(duration!=null){
            return new ResponseEntity<>(paymentService.getPaymentByDuration(duration),HttpStatus.OK);
        }
        return new ResponseEntity<>(paymentService.getPaymentMethods(), HttpStatus.OK);
    }

    @PostMapping("/payment-methods")
    public ResponseEntity<String> addPayments(@RequestBody Payment payment) {
        try {
            paymentService.savePayment(payment);
            return new ResponseEntity<String>("Payment Inserted Successfully.", HttpStatus.OK);
        } catch (RuntimeException re) {
            return new ResponseEntity<String>(re.getMessage(), HttpStatus.FORBIDDEN);
        }
    }


    @PutMapping("/payment-methods")
    public ResponseEntity<Object> updatePayment(@RequestBody Payment payment, @RequestParam(value = "payment-methods") Long planId) {
        try {
            paymentService.updatePayment(payment, planId);
            return ResponseEntity.ok("Payment Updated Successfully.");
        } catch (RuntimeException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}

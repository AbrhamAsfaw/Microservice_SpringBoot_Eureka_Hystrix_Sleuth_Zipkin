package com.abrham.ps.api.service;

import com.abrham.ps.api.entity.Payment;
import com.abrham.ps.api.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    private Logger log = LoggerFactory.getLogger(PaymentService.class);



    public Payment doPayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        log.info("Payment-Service request: {}", new ObjectMapper().writeValueAsString(payment));
        return repository.save(payment);
    }


    public String paymentProcessing(){
        return new Random().nextBoolean()?"success":"False";
    }

    public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {

        Payment payment = repository.findByOrderId(orderId);
        log.info("Payment-Service request: {}", new ObjectMapper().writeValueAsString(payment));

        return payment;
    }
}

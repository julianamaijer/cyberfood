package com.jules.cyberfood.domain.repository;

import com.jules.cyberfood.domain.model.Payment;

import java.util.List;

public interface PaymentRepository {

    List<Payment> allPayments();
    Payment findById(Long id);
    Payment addPayment(Payment payment);
    void removePayment(Payment payment);

}

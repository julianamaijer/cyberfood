package com.jules.cyberfood.infrastructure.repository;

import com.jules.cyberfood.domain.model.Payment;
import com.jules.cyberfood.domain.repository.PaymentRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Payment> allPayments() {
        TypedQuery<Payment> query = entityManager.createQuery("from Payment", Payment.class);
        return query.getResultList();
    }

    @Override
    public Payment findById(Long id) {
        return entityManager.find(Payment.class, id);
    }

    @Override
    public Payment addPayment(Payment payment) {
        return entityManager.merge(payment);
    }

    @Override
    public void removePayment(Payment payment) {
        payment = findById(payment.getId());
        entityManager.remove(payment);
    }
}

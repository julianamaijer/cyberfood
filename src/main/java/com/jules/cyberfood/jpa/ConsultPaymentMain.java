package com.jules.cyberfood.jpa;

import com.jules.cyberfood.CyberfoodApplication;
import com.jules.cyberfood.domain.model.Payment;
import com.jules.cyberfood.domain.repository.PaymentRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultPaymentMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(CyberfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        PaymentRepository consultPayment = applicationContext.getBean(PaymentRepository.class);
        List<Payment> paymentList = consultPayment.allPayments();

        for (Payment payment : paymentList){
            System.out.println(payment.getDescription());
        }
    }
}

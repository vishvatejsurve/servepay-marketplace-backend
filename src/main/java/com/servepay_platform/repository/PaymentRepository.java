package com.servepay_platform.repository;

import com.servepay_platform.entity.Payment;
import com.servepay_platform.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByRequest(ServiceRequest request);
}

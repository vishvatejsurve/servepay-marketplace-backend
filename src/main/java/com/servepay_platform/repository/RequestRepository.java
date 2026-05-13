package com.servepay_platform.repository;

import com.servepay_platform.entity.ServiceOffering;
import com.servepay_platform.entity.ServiceRequest;
import com.servepay_platform.entity.User;
import com.servepay_platform.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<ServiceRequest,Long> {
    List<ServiceRequest> findByStatus(RequestStatus status);

    boolean existsByCustomerAndServiceAndStatus(User customer, ServiceOffering service, RequestStatus requestStatus);
}

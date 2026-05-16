package com.servepay_platform.repository;

import com.servepay_platform.entity.Review;
import com.servepay_platform.entity.ServiceOffering;
import com.servepay_platform.entity.User;
import com.servepay_platform.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByService(ServiceOffering service);
}

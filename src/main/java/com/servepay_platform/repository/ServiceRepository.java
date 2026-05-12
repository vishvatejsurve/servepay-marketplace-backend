package com.servepay_platform.repository;

import com.servepay_platform.entity.ServiceOffering;
import com.servepay_platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceOffering,Long> {
    List<ServiceOffering> findByProvider(User provider);
}

package com.servepay_platform.service;

import com.servepay_platform.dto.reviewDTO.ReviewRequestDTO;

public interface ReviewService {
    void addReview(ReviewRequestDTO dto,String customerEmail);
}

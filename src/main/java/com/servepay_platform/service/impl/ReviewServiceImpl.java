package com.servepay_platform.service.impl;

import com.servepay_platform.dto.reviewDTO.ReviewRequestDTO;
import com.servepay_platform.entity.Review;
import com.servepay_platform.entity.ServiceOffering;
import com.servepay_platform.entity.User;
import com.servepay_platform.enums.RequestStatus;
import com.servepay_platform.repository.RequestRepository;
import com.servepay_platform.repository.ReviewRepository;
import com.servepay_platform.repository.ServiceRepository;
import com.servepay_platform.repository.UserRepository;
import com.servepay_platform.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final RequestRepository requestRepository;
    @Override
    public void addReview(ReviewRequestDTO dto, String customerEmail) {

            User customer = userRepository.findByEmail(customerEmail)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            ServiceOffering service = serviceRepository.findById(dto.getServiceId())
                    .orElseThrow(() -> new RuntimeException("Service not found"));

            // check payment completed
            boolean completed = requestRepository
                    .existsByCustomerAndServiceAndStatus(
                            customer,
                            service,
                            RequestStatus.PAID
                    );

            if (!completed) {
                throw new RuntimeException(
                        "You can review only after payment"
                );
            }

            Review review = Review.builder()
                    .rating(dto.getRating())
                    .comment(dto.getComment())
                    .customer(customer)
                    .provider(service.getProvider())
                    .service(service)
                    .build();

            reviewRepository.save(review);

            // update average rating
            List<Review> reviews =
                    reviewRepository.findByService(service);

            double avg = reviews.stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);

            service.setAverageRating(avg);

            serviceRepository.save(service);
        }
    }

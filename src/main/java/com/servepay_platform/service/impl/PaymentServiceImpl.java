package com.servepay_platform.service.impl;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.servepay_platform.dto.payementDTO.PaymentOrderResponseDTO;
import com.servepay_platform.entity.Payment;
import com.servepay_platform.entity.ServiceRequest;
import com.servepay_platform.enums.PaymentStatus;
import com.servepay_platform.enums.RequestStatus;
import com.servepay_platform.repository.PaymentRepository;
import com.servepay_platform.repository.RequestRepository;
import com.servepay_platform.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Value("${razorpay.key.id}")
    private String razorpayKey;

    @Value("${razorpay.key.secret}")
    private String razorpaySecret;

    private final RequestRepository requestRepository;
    private final PaymentRepository paymentRepository;
    @Override
    public PaymentOrderResponseDTO createOrder(Long requestId) {
        
        try {

            ServiceRequest request = requestRepository.findById(requestId)
                    .orElseThrow(() -> new RuntimeException("Request not found"));

            RazorpayClient razorpay = new RazorpayClient(razorpayKey, razorpaySecret);

            JSONObject options = new JSONObject();

            int amount = request.getService().getPrice().intValue() * 100;

            options.put("amount", amount);
            options.put("currency", "INR");
            options.put("receipt", "txn_" + requestId);

            Order order = razorpay.orders.create(options);

            Payment payment = Payment.builder()
                    .razorpayOrderId(order.get("id"))
                    .amount(request.getService().getPrice())
                    .status(PaymentStatus.PENDING)
                    .request(request)
                    .build();

            paymentRepository.save(payment);

            return new PaymentOrderResponseDTO(
                    order.get("id"),
                    amount,
                    "INR",
                    razorpayKey
            );

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void verifyPayment(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature, Long requestId) {
        ServiceRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        Payment payment = paymentRepository.findByRequest(request);

        // later -> signature verification

        payment.setRazorpayPaymentId(razorpayPaymentId);
        payment.setStatus(PaymentStatus.SUCCESS);

        request.setStatus(RequestStatus.PAID);

        paymentRepository.save(payment);
        requestRepository.save(request);
    }
}

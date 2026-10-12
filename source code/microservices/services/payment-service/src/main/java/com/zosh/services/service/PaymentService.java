package com.zosh.services.service;

import com.zosh.common_lib.exception.PaymentException;
import com.zosh.common_lib.payload.request.PaymentInitiateRequest;
import com.zosh.common_lib.payload.request.PaymentVerifyRequest;
import com.zosh.common_lib.payload.response.PaymentDTO;
import com.zosh.common_lib.payload.response.PaymentInitiateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PaymentService {

    PaymentInitiateResponse initiatePayment(PaymentInitiateRequest request) throws PaymentException;

    PaymentDTO verifyPayment(PaymentVerifyRequest request) throws PaymentException;


    Page<PaymentDTO> getAllPayments(Pageable pageable);



    Map<Long, PaymentDTO> getPaymentsByBookingIds(List<Long> bookingIds);
}

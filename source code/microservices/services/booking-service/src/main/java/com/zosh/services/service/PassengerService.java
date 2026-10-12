package com.zosh.services.service;

import com.zosh.common_lib.exception.ResourceNotFoundException;
import com.zosh.common_lib.payload.request.PassengerRequest;
import com.zosh.common_lib.payload.response.PassengerResponse;
import com.zosh.services.model.Passenger;

public interface PassengerService {

    PassengerResponse createPassenger(PassengerRequest request, Long userId)
            throws ResourceNotFoundException;

    Passenger findOrCreatePassengerEntity(PassengerRequest request, Long userId);

    Passenger findExistingPassenger(PassengerRequest request);

    boolean existsById(Long id);

    long count();
}

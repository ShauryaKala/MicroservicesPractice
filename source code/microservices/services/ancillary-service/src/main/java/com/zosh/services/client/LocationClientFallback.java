package com.zosh.services.client;

import com.zosh.common_lib.exception.AirportException;
import com.zosh.common_lib.payload.response.AirportResponse;
import org.springframework.stereotype.Component;

@Component
public class LocationClientFallback implements LocationClient {

    @Override
    public AirportResponse getAirportById(Long id) throws AirportException {
        return null;
    }
}

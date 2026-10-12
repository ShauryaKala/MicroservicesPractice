package com.zosh.services.service;

import com.zosh.common_lib.dto.UserDTO;
import com.zosh.common_lib.exception.UserException;
import com.zosh.common_lib.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String email, String password) throws UserException;
    AuthResponse signup(UserDTO req) throws UserException;
}

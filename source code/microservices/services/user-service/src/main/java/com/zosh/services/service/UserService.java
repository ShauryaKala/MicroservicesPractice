package com.zosh.services.service;

import com.zosh.common_lib.enums.UserRole;
import com.zosh.common_lib.exception.UserException;
import com.zosh.services.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id) throws UserException;
    List<User> getUsers() throws UserException;
}

package com.zosh.services.service.impl;

import com.zosh.common_lib.enums.UserRole;
import com.zosh.common_lib.exception.UserException;
import com.zosh.services.model.User;
import com.zosh.services.repository.UserRepository;
import com.zosh.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("User not found with email: " + email);
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws UserException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found with id: " + id));
    }

    @Override
    public List<User> getUsers() throws UserException {
        return userRepository.findAll();
    }
}

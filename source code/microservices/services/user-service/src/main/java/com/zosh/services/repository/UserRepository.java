package com.zosh.services.repository;

import com.zosh.common_lib.enums.UserRole;
import com.zosh.services.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Set<User> findByRole(UserRole role);
}

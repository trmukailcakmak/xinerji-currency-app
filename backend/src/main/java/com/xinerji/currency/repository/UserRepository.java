package com.xinerji.currency.repository;

import com.xinerji.currency.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String username);
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
}

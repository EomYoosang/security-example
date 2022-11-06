package com.eomyoosang.securityexample.repository;

import com.eomyoosang.securityexample.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
}

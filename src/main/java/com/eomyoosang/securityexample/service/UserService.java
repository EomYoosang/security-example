package com.eomyoosang.securityexample.service;

import com.eomyoosang.securityexample.domain.User;
import com.eomyoosang.securityexample.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;

    public User findOne(Long id) {
        return userJpaRepository.findById(id).get();
    }
}

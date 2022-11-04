package com.eomyoosang.securityexample.service;

import com.eomyoosang.securityexample.domain.User;
import com.eomyoosang.securityexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }
}

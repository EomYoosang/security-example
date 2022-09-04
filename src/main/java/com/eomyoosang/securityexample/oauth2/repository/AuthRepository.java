package com.eomyoosang.securityexample.oauth2.repository;

import com.eomyoosang.securityexample.domain.User;
import com.eomyoosang.securityexample.oauth2.soical.SocialType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AuthRepository extends PagingAndSortingRepository<User, Long> {
    public Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}

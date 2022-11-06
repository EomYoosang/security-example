package com.eomyoosang.securityexample.api;

import com.eomyoosang.securityexample.dto.TokenResponse;
import com.eomyoosang.securityexample.error.exception.ExpiredRefreshTokenException;
import com.eomyoosang.securityexample.error.exception.InvalidRefreshTokenException;
import com.eomyoosang.securityexample.security.jwt.service.JwtTokenProvider;
import com.eomyoosang.securityexample.security.jwt.service.PrincipalDetailsService;
import com.eomyoosang.securityexample.service.RedisService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthApiController {

    private final RedisService redisService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PrincipalDetailsService principalDetailsService;

    @PostMapping("/refresh")
    public TokenResponse refreshToken(
            @RequestHeader(value = "Authorization") String token,
            @RequestHeader(value = "Refresh-Token") String refreshToken) {

        Long userId;
        if (jwtTokenProvider.existsRefreshToken(refreshToken)) {
             userId = Long.valueOf(redisService.getValues(refreshToken));
        } else {
            throw new InvalidRefreshTokenException();
        }

        try {
            jwtTokenProvider.validateToken(refreshToken);
        } catch (ExpiredJwtException e) {
            throw new ExpiredRefreshTokenException();
        }

        redisService.delValues(refreshToken);
        Long userIdFromAccessToken;
        try {
            jwtTokenProvider.getUserIdFromExpiredToken(token.substring(7, token.length()));
        } catch (ExpiredJwtException e) {
            userIdFromAccessToken = Long.parseLong(e.getClaims().getSubject());
        }

        return new TokenResponse(
                jwtTokenProvider.createToken(userId),
                jwtTokenProvider.createRefreshToken(userId)
        );
    }
}

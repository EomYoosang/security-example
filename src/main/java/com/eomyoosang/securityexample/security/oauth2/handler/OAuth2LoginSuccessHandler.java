package com.eomyoosang.securityexample.security.oauth2.handler;

import com.eomyoosang.securityexample.config.AppProperties;
import com.eomyoosang.securityexample.security.jwt.service.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;
    private final AppProperties appProperties;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String accessToken = jwtTokenProvider.createToken(authentication);
        String refreshToken = jwtTokenProvider.createRefreshToken();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;
//
        TokenResponse tokenResponse = new TokenResponse(accessToken, refreshToken);
        if (jsonConverter.canWrite(tokenResponse.getClass(), jsonMimeType)) {
            jsonConverter.write(tokenResponse, jsonMimeType, new ServletServerHttpResponse(response));
        }
    }

    @Data
    @AllArgsConstructor
    static class TokenResponse {
        String accessToken;
        String refreshToken;
    }
}

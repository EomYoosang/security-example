package com.eomyoosang.securityexample.security.oauth2.handler;

import com.eomyoosang.securityexample.error.errorcode.ErrorCode;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(ErrorCode.EXPIRED_SOCIAL_TOKEN.getHttpStatus().value());

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", ErrorCode.EXPIRED_SOCIAL_TOKEN.getMessage());
        responseJson.put("code", ErrorCode.EXPIRED_SOCIAL_TOKEN.getHttpStatus().value());

        response.getWriter().print(responseJson);
    }

    @AllArgsConstructor
    static class Message {
        String message;
    }
}

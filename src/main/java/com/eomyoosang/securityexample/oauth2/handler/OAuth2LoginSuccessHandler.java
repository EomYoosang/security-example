package com.eomyoosang.securityexample.oauth2.handler;

import com.eomyoosang.securityexample.config.AppProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.oauth2.sdk.TokenResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
//    private TokenProvider tokenProvider;
    private final AppProperties appProperties;

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        String accessToken = tokenProvider.createToken(authentication);
//        String refreshToken = tokenProvider.createRefreshToken();
        String accessToken = "";
        String refreshToken = "";
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        // application/json(ajax) 요청일 경우 아래의 처리!
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;
//
        TokenResponse tokenResponse = new TokenResponse(accessToken, refreshToken);
        if (jsonConverter.canWrite(tokenResponse.getClass(), jsonMimeType)) {
            jsonConverter.write(tokenResponse, jsonMimeType, new ServletServerHttpResponse(response));
        }
    }
//    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
//                .map(Cookie::getValue);
//        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
//            throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
//        }
//        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
//        String targetUrl = "http://localhost:3000/auth";
//        String token = tokenProvider.createToken(authentication);
//        OAuth2UserDetails principalDetails = (OAuth2UserDetails) authentication.getPrincipal();
//        String nickname = principalDetails.getUsername();
//        System.out.println(nickname);
//        return UriComponentsBuilder.fromUriString(targetUrl)
//                .queryParam("token", token)
//                .queryParam("nickname", nickname)
//                .build().encode().toUriString();
//    }
//    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
//        super.clearAuthenticationAttributes(request);
//    }
//    private boolean isAuthorizedRedirectUri(String uri) {
//        URI clientRedirectUri = URI.create(uri);
//        return appProperties.getOauth2().getAuthorizedRedirectUris()
//                .stream()
//                .anyMatch(authorizedRedirectUri -> {
//                    // Only validate host and port. Let the clients use different paths if they want to
//                    URI authorizedURI = URI.create(authorizedRedirectUri);
//                    if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
//                            && authorizedURI.getPort() == clientRedirectUri.getPort()) {
//                        return true;
//                    }
//                    return false;
//                });
//    }

    @Data
    @AllArgsConstructor
    static class TokenResponse {
        String accessToken;
        String refreshToken;
    }
}

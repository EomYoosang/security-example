package com.eomyoosang.securityexample.oauth2.service;

import com.eomyoosang.securityexample.oauth2.dto.KakaoUserInfo;
import com.eomyoosang.securityexample.oauth2.soical.SocialType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class KakaoLoadStrategy extends SocialLoadStrategy{

    protected KakaoUserInfo sendRequestToSocialSite(HttpEntity request){
        try {
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(SocialType.KAKAO.getUserInfoUrl(),// -> /v2/user/me
                    SocialType.KAKAO.getMethod(),
                    request,
                    RESPONSE_TYPE);

            return new KakaoUserInfo(response.getBody());//카카오는 id를 PK로 사용

        } catch (Exception e) {
            throw e;
        }
    }
}
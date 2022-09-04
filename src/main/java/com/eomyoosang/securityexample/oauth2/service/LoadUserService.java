package com.eomyoosang.securityexample.oauth2.service;

import com.eomyoosang.securityexample.oauth2.authentication.AccessTokenSocialTypeToken;
import com.eomyoosang.securityexample.oauth2.dto.OAuth2UserInfo;
import com.eomyoosang.securityexample.oauth2.soical.SocialType;
import com.eomyoosang.securityexample.oauth2.user.OAuth2UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LoadUserService {

    private final RestTemplate restTemplate = new RestTemplate();

    private SocialLoadStrategy socialLoadStrategy;//추상 클래스, 로그인을 진행하는 사이트레 따라 달라짐


    public OAuth2UserDetails getOAuth2UserDetails(AccessTokenSocialTypeToken authentication)  {

        SocialType socialType = authentication.getSocialType();

        setSocialLoadStrategy(socialType);//SocialLoadStrategy 설정

        OAuth2UserInfo userInfo = socialLoadStrategy.getUserInfo(authentication.getAccessToken());//PK 가져오기

        return OAuth2UserDetails.builder() //PK와 SocialType을 통해 회원 생성
                .socialId(userInfo.getSocialId())
                .socialType(socialType)
                .username(userInfo.getName())
                .email(userInfo.getEmail())
                .image(userInfo.getImage())
                .build();
    }

    private void setSocialLoadStrategy(SocialType socialType) {
        switch (socialType){
            case KAKAO:
                this.socialLoadStrategy = new KakaoLoadStrategy();
                break;
            default:
                throw new IllegalArgumentException("지원하지 않는 로그인 형식입니다");
        }
    }
}

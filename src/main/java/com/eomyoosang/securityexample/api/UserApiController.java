package com.eomyoosang.securityexample.api;

import com.eomyoosang.securityexample.domain.User;
import com.eomyoosang.securityexample.security.annotation.AuthUser;
import com.eomyoosang.securityexample.security.user.PrincipalDetails;
import com.eomyoosang.securityexample.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @GetMapping(value = "/user")
    public UserDto getUserInfo(
//            @AuthUser PrincipalDetails principalDetails
    ) {
//        User user = userService.findOne(principalDetails.getId());
//        return new UserDto(user.getName(), user.getProfileImage());
        return new UserDto("hi", "hi");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserDto {
        private String name;
        private String profileImage;
    }
}

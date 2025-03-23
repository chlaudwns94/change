package com.korit.mcdonaldkiosk.security.oAuth2;

import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.entity.OAuth2;
import com.korit.mcdonaldkiosk.repository.admin.AdminRepository;
import com.korit.mcdonaldkiosk.security.pricipal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DefaultOAuth2UserService defaultOAuth2UserService;

    /**
     * OAuth2 로그인 후 사용자 정보를 가져오는 메서드
     *
     * @param userRequest OAuth2UserRequest
     * @return OAuth2User
     * @throws OAuth2AuthenticationException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String email = null;
        String oauth2Name = null;
        String oauth2Provider = userRequest.getClientRegistration().getRegistrationId();

        // 기본 OAuth2UserService를 사용하여 사용자 속성 가져오기
        Map<String, Object> attributes = getDefaultOAuth2User(userRequest).getAttributes();

        // Naver 로그인 처리
        if(oauth2Provider.equalsIgnoreCase("naver")) {
            attributes = (Map<String, Object>) attributes.get("response");
            oauth2Name = (String) attributes.get("id");
            email = (String) attributes.get("email");
        }

        // Google 로그인 처리
        if(oauth2Provider.equalsIgnoreCase("google")) {
            oauth2Name = (String) attributes.get("sub");
            email = (String) attributes.get("email");
        }

        // adminName을 OAuth2 프로바이더 이름과 사용자 이름을 결합하여 생성
        final String adminName = oauth2Provider + "_" + oauth2Name;
        final String finalEmail = email;

        // 기존 Admin 존재 여부 확인, 없으면 새 Admin 생성
        Admin admin = adminRepository
                .findAdminByAdminName(adminName)
                .orElseGet(() -> {
                    Admin newUser = Admin.builder()
                            .adminName(adminName)
                            .tradeName(adminName)
                            .email(finalEmail)
                            .build();
                    System.out.println(newUser);
                    adminRepository.save(newUser);  // 새 Admin 저장
                    return newUser;
                });

        // OAuth2 정보 생성
        OAuth2 oAuth2 = OAuth2.builder()
                .adminId(admin.getAdminId())
                .oAuth2Name(oauth2Name)
                .providerName(oauth2Provider)
                .build();

        System.out.println(oAuth2);

        // OAuth2 정보 저장
        adminRepository.saveOAuth2(oAuth2);

        // PrincipalUser 반환 (Spring Security에서 인증을 위해 사용)
        return PrincipalUser.builder()
                .admin(admin)
                .oAuth2(oAuth2)
                .name(oauth2Name)
                .attributes(attributes)
                .build();
    }

    /**
     * 기본 OAuth2UserService를 사용하여 OAuth2 사용자 정보를 가져오는 메서드
     *
     * @param userRequest OAuth2UserRequest
     * @return OAuth2User
     * @throws OAuth2AuthenticationException
     */
    private OAuth2User getDefaultOAuth2User(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        return defaultOAuth2UserService.loadUser(userRequest);
    }
}

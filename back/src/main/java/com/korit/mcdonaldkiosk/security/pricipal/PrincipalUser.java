package com.korit.mcdonaldkiosk.security.pricipal;

import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.entity.OAuth2;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
@Getter
public class PrincipalUser implements UserDetails, OAuth2User {
    private Admin admin;
    private OAuth2 oAuth2;
    private Map<String, Object> attributes;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;



    @Override
    public String getPassword() {
        return admin.getAdminPassword();
    }

    @Override
    public String getUsername() {
        return admin.getAdminName();
    }


    @Override
    public String getName() {
        return attributes.get("id").toString();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public boolean isEnabled() {
        return admin.getAccountEnabled() == 1;
    }
}













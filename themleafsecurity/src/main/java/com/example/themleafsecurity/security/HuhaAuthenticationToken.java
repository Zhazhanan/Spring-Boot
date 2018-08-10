package com.example.themleafsecurity.security;

import com.example.themleafsecurity.dto.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

public class HuhaAuthenticationToken extends AbstractAuthenticationToken {

    private User user;

    public HuhaAuthenticationToken(User user) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.user = user;
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }
}

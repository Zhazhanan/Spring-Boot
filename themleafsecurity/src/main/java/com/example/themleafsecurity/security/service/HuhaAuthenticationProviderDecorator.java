package com.example.themleafsecurity.security.service;

import com.example.themleafsecurity.security.HuhaAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class HuhaAuthenticationProviderDecorator implements AuthenticationProvider {

    private AuthenticationProvider authenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof HuhaAuthenticationToken) {
            return authentication;
        } else {
            return authenticationProvider.authenticate(authentication);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        if (aClass.equals(HuhaAuthenticationToken.class)) {
            return true;
        } else {
            return authenticationProvider.supports(aClass);
        }
    }

    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }
}

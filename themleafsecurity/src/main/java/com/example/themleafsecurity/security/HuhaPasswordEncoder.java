package com.example.themleafsecurity.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author WangKun
 * @create 2018-08-09
 * @desc custom encryption
 **/
public class HuhaPasswordEncoder implements PasswordEncoder {
    private static final Logger LOGGER = LoggerFactory.getLogger(HuhaPasswordEncoder.class);

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return passwordEncoder.matches(charSequence, "{MD5}" + s);
    }
}

package com.dsmpear.main.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    @Override
    public AuthDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return null;
    }
}

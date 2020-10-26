package com.dsmpear.main.domain.auth.service;

import com.dsmpear.main.domain.auth.dto.request.SignInRequest;
import com.dsmpear.main.domain.auth.dto.response.TokenResponse;
import com.dsmpear.main.domain.auth.entity.refreshtoken.RefreshTokenRepository;
import com.dsmpear.main.domain.user.entity.UserRepository;
import com.dsmpear.main.global.security.JwtTokenProvider;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.BDDMockito.*;

public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RefreshTokenRepository refreshTokenRepository;
    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Test(expected = NullPointerException.class)
    public void signIn() {
        SignInRequest request = new SignInRequest("1111", "1202@dsm.hs.kr");
        given(authService.signIn(request)).willReturn(new TokenResponse());

        authService.signIn(request);
    }
}
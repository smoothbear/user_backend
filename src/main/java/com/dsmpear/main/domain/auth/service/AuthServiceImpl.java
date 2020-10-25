package com.dsmpear.main.domain.auth.service;

import com.dsmpear.main.domain.auth.entity.refreshtoken.RefreshToken;
import com.dsmpear.main.domain.auth.entity.refreshtoken.RefreshTokenRepository;
import com.dsmpear.main.domain.auth.dto.request.SignInRequest;
import com.dsmpear.main.domain.auth.dto.response.TokenResponse;
import com.dsmpear.main.domain.auth.exceptions.UserNotFoundException;
import com.dsmpear.main.domain.user.domain.User;
import com.dsmpear.main.domain.user.domain.UserRepository;
import com.dsmpear.main.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${auth.jwt.exp.refresh}")
    private Long refreshExp;


    @Override
    public TokenResponse signIn(SignInRequest dto) {
        return userRepository.findByEmail(dto.getEmail())
                .filter(user -> passwordEncoder.matches(dto.getPassword(), user.getPassword()))
                    .map(User::getId)
                    .map(id -> {
                        String refreshToken = jwtTokenProvider.generateRefreshToken(id);
                        return new RefreshToken(null, refreshToken, refreshExp);
                    })
                    .map(refreshTokenRepository::save)
                    .map(refreshToken -> {
                        String accessToken = jwtTokenProvider.generateAccessToken(refreshToken.getId());
                        return new TokenResponse(accessToken, refreshToken.getRefreshToken());
                    })
                .orElseThrow(UserNotFoundException::new);
    }
}

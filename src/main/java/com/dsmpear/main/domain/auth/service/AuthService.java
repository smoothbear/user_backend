package com.dsmpear.main.domain.auth.service;

import com.dsmpear.main.domain.auth.dto.request.SignInRequest;
import com.dsmpear.main.domain.auth.dto.response.TokenResponse;

public interface AuthService {
    TokenResponse signIn(SignInRequest dto);
}

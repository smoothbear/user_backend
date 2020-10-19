package com.dsmpear.main.domain.auth.controller;

import com.dsmpear.main.domain.auth.dto.request.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class TokenControllerTest {
    @Test
    public void signIn() {
        final SignInRequest signInRequest = new SignInRequest("test", "1111");
    }
}
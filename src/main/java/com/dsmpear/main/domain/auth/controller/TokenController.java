package com.dsmpear.main.domain.auth.controller;

import com.dsmpear.main.domain.auth.dto.request.SignInRequest;
import com.dsmpear.main.domain.auth.dto.response.TokenResponse;
import com.dsmpear.main.domain.auth.service.AuthService;
import com.dsmpear.main.domain.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class TokenController {
    private final AuthService authService;

    @PostMapping
    public TokenResponse signIn(@RequestBody @Valid SignInRequest dto) {
        return authService.signIn(dto);
    }

    @PutMapping
    public void test() {

    }
}
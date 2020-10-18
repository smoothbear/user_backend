package com.dsmpear.main.domain.auth.dto.request;

import lombok.Getter;

@Getter
public class SignInRequest {
    private String id;
    private String password;
}
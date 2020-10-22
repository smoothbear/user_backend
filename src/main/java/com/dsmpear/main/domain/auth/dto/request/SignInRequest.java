package com.dsmpear.main.domain.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Getter @AllArgsConstructor @NoArgsConstructor
public class SignInRequest {
    private String password;

    @Email
    private String email;
}
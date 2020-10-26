package com.dsmpear.main.domain.auth.controller;

import com.dsmpear.main.domain.auth.dto.request.SignInRequest;
import com.dsmpear.main.domain.auth.entity.refreshtoken.RefreshToken;
import com.dsmpear.main.domain.auth.entity.refreshtoken.RefreshTokenRepository;
import com.dsmpear.main.domain.user.entity.User;
import com.dsmpear.main.domain.user.entity.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TokenController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TokenControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private RefreshTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        tokenRepository.save(
                RefreshToken.builder()
                        .refreshToken("asdf")
                        .refreshExp(10000L)
                        .build()
        );

        userRepository.save(
                User.builder()
                     .email("asdf@dsm.hs.kr")
                    .password("1111")
                .build()
        );
    }

    @AfterEach
    public void clean() {
        tokenRepository.deleteAll();
    }

    @Test
    public void signInTest() throws Exception {
        signIn();
    }

    private MvcResult signIn() throws Exception {
        String url = "http://localhost:" + port;

        SignInRequest request = new SignInRequest("1111", "asdf@dsm.hs.kr");

        return mvc.perform(post(url + "/auth")
                .content(new ObjectMapper()
                        .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                        .writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }
}

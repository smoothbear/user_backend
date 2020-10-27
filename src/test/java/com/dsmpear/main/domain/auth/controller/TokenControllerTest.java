package com.dsmpear.main.domain.auth.controller;

import com.dsmpear.main.domain.auth.dto.request.SignInRequest;
import com.dsmpear.main.domain.auth.entity.refreshtoken.RefreshToken;
import com.dsmpear.main.domain.auth.entity.refreshtoken.RefreshTokenRepository;
import com.dsmpear.main.domain.user.entity.User;
import com.dsmpear.main.domain.user.entity.UserRepository;
import com.dsmpear.main.global.security.TokenFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TokenControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RefreshTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        userRepository.save(
                User.builder()
                     .email("asdf@dsm.hs.kr")
                    .password(passwordEncoder.encode("1111"))
                .build()
        );
    }

    @AfterEach
    public void clean() {
        tokenRepository.deleteAll();
    }

    /* @Test
    public void signInTest() throws Exception {
        signIn();
    }

    private MvcResult signIn() throws Exception {
        String url = "http://localhost:" + port;

        SignInRequest request = new SignInRequest(passwordEncoder.encode("1111"), "asdf@dsm.hs.kr");

        return mvc.perform(post(url + "/auth")
                .content(new ObjectMapper()
                        .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                        .writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }
    */
    
    @Test
    public void auth() throws Exception {
        String url = "http://localhost:" + port;

        mvc.perform(put(url + "/auth"))
                .andExpect(status().isOk());
    }
}

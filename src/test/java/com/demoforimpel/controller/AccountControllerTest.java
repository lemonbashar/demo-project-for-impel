package com.demoforimpel.controller;


import com.demoforimpel.DemoForImpelApplication;
import com.demoforimpel.data.UserInfo;
import com.demoforimpel.repository.UserRepository;
import com.demoforimpel.service.security.AuthoritiesDefinition;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = MOCK,
        classes = DemoForImpelApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void registerUsers() throws Exception {
        createUser(adminCreation());
        createUser(managerCreation());
    }

    private void createUser(UserInfo userInfo) throws Exception {
        mockMvc.perform(post("/api/account-controller/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(userInfo)).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    private UserInfo managerCreation() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("manager");
        userInfo.setPassword("manager");
        userInfo.setAuthorities(new HashSet<>(Arrays.asList(AuthoritiesDefinition.ROLE_USER, AuthoritiesDefinition.ROLE_MANAGER)));
        return userInfo;
    }

    private UserInfo adminCreation() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("admin");
        userInfo.setPassword("admin");
        userInfo.setAuthorities(new HashSet<>(Arrays.asList(AuthoritiesDefinition.ROLE_ADMIN, AuthoritiesDefinition.ROLE_USER)));
        return userInfo;
    }
}

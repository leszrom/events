package com.crud.events.controller;

import com.crud.events.domain.dto.MemberRequest;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTestSuite {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_create_new_member() throws Exception {
        //Given
        MemberRequest memberRequest = new MemberRequest("Firstname", "Lastname");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(memberRequest);

        //When
        mockMvc.perform(post("/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                //Then
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.greaterThanOrEqualTo(1)));
    }

}
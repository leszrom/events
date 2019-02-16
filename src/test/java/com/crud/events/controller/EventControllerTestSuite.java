package com.crud.events.controller;

import com.crud.events.domain.dto.EventRequest;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class EventControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_create_new_event() throws Exception {
        //Given
        EventRequest eventRequest = new EventRequest("name", "description", LocalDateTime.parse("2018-01-23T14:30"));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(eventRequest);

        //When
        mockMvc.perform(post("/v1/events")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))

                //Then
                .andExpect(status().is(200));
    }
}
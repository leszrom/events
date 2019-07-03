package com.crud.events.controller;

import com.crud.events.domain.Event;
import com.crud.events.domain.Member;
import com.crud.events.repository.EventRepository;
import com.crud.events.repository.MemberRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerRestTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void should_create_new_event() throws Exception {
        //Given
        String jsonContent = "{\"name\":\"name\",\"description\":\"description\",\"date\":\"2018-01-23 14:30\"}";

        //When
        mockMvc.perform(post("/v1/events")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))

                //Then
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", Matchers.greaterThanOrEqualTo(1)));
    }

    @Test
    @Transactional
    public void should_add_member_to_existing_event_by_event_id() throws Exception {
        //Given
        Member member = memberRepository.save(new Member("Firstname", "Lastname"));
        Event event = eventRepository.save(
                new Event("Name", "Description", LocalDateTime.parse("2018-01-23T14:30")));

        //When
        mockMvc.perform(put(
                "/v1/events/{eventId}/members?memberId={memberId}", event.getId(), member.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))

                //Then
                .andExpect(status().is(200));
        Assert.assertTrue(event.getMembers().contains(member));
    }

    @Test
    @Transactional
    public void should_remove_member_from_existing_event_by_event_id() throws Exception {
        //Given
        Member member = new Member("Firstname", "Lastname");
        Event event = new Event("Name", "Description", LocalDateTime.parse("2018-01-23T14:30"));
        event.getMembers().add(member);
        eventRepository.save(event);

        //When
        mockMvc.perform(delete(
                "/v1/events/{eventId}/members?memberId={memberId}", event.getId(), member.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))

                //Then
                .andExpect(status().is(200));
        Assert.assertEquals(0, event.getMembers().size());
    }
}

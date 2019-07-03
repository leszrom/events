package com.crud.events.controller;

import com.crud.events.domain.Member;
import com.crud.events.domain.Permission;
import com.crud.events.domain.dto.MemberRequest;
import com.crud.events.repository.MemberRepository;
import com.google.gson.Gson;
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

import static com.crud.events.domain.Role.VIP;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerRestTestSuite {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
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
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", Matchers.greaterThanOrEqualTo(1)));
    }

    @Test
    @Transactional
    public void should_return_member_by_given_id() throws Exception {
        //Given
        Member member = memberRepository.save(new Member("Firstname", "Lastname"));

        //When
        mockMvc.perform(get(
                "/v1/members/{memberId}", member.getId())
                .contentType(MediaType.APPLICATION_JSON))

                //Then
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", Matchers.greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$.firstname", Matchers.is("Firstname")))
                .andExpect(jsonPath("$.lastname", Matchers.is("Lastname")));
    }

    @Test
    @Transactional
    public void should_return_all_members() throws Exception {
        //Given
        memberRepository.save(new Member("Firstname", "Lastname"));

        //When
        mockMvc.perform(get("/v1/members")
                .contentType(MediaType.APPLICATION_JSON))

                //Then
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$[0].firstname", is("Firstname")))
                .andExpect(jsonPath("$[0].lastname", is("Lastname")));
    }

    @Test
    @Transactional
    public void should_delete_member_by_given_id() throws Exception {
        //Given
        Member member = memberRepository.save(new Member("Firstname", "Lastname"));

        //When
        mockMvc.perform(delete("/v1/members/{memberId}", member.getId())
                .contentType(MediaType.APPLICATION_JSON))

                //Then
                .andExpect(status().is(200));
        Assert.assertEquals(0, memberRepository.findAll().size());
    }

    @Test
    @Transactional
    public void should_update_member_details() throws Exception {
        //Given
        Member member = memberRepository.save(new Member("Firstname", "Lastname"));
        MemberRequest memberRequest = new MemberRequest("new_Firstname", "new_Lastname");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(memberRequest);

        //When
        mockMvc.perform(put("/v1/members/{memberId}", member.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                //Then
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(member.getId().intValue())))
                .andExpect(jsonPath("$.firstname", is("new_Firstname")))
                .andExpect(jsonPath("$.lastname", is("new_Lastname")));
    }

    @Test
    @Transactional
    public void should_add_permission_for_member() throws Exception {
        //Given
        Member member = memberRepository.save(new Member("Firstname", "Lastname"));

        //When
        mockMvc.perform(put(
                "/v1/members/{memberId}/permissions?role={role}", member.getId(), VIP)
                .contentType(MediaType.APPLICATION_JSON))

                //Then
                .andExpect(status().is(200));
        Assert.assertEquals(1, member.getPermissions().size());
    }

    @Test
    @Transactional
    public void should_revoke_permission_for_member() throws Exception {
        //Given
        Member member = new Member("Firstname", "Lastname");
        member.getPermissions().add(new Permission(VIP));
        memberRepository.save(member);

        //When
        mockMvc.perform(delete(
                "/v1/members/{memberId}/permissions?role={role}", member.getId(), VIP)
                .contentType(MediaType.APPLICATION_JSON))

                //Then
                .andExpect(status().is(200));
        Assert.assertEquals(0, member.getPermissions().size());
    }
}

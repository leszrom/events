package com.crud.events.domain.dto;

public class MemberRequest {
    private String firstname;
    private String lastname;

    public MemberRequest() {
    }

    public MemberRequest(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}

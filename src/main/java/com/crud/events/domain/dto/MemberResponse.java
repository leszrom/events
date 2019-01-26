package com.crud.events.domain.dto;

public class MemberResponse {
    private long id;
    private String firstname;
    private String lastname;

    public MemberResponse() {
    }

    public MemberResponse(long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}

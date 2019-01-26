package com.crud.events.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "Firstname")
    private String firstname;

    @Column(name = "Lastname")
    private String lastname;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Members_Permissions",
            joinColumns = @JoinColumn(name = "MemberID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PermissionID", referencedColumnName = "ID"))
    private List<Permission> permissions = new ArrayList<>();

    @ManyToMany(mappedBy = "members")
    private List<Event> events = new ArrayList<>();

    public Member() {
    }

    public Member(String firstname, String lastname) {
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public List<Event> getEvents() {
        return events;
    }
}

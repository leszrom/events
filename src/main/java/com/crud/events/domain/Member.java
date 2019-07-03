package com.crud.events.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Member.retrieveMembersWithRole",
                query = "SELECT m FROM Members m JOIN m.permissions P WHERE P.role = :ROLE")
})

@Entity(name = "Members")
@Table(name = "Members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

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

    public Member(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }


    public Long getId() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}

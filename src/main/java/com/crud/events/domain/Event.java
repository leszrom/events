package com.crud.events.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Events_Members",
            joinColumns = @JoinColumn(name = "EventID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "MemberID", referencedColumnName = "ID"))
    private List<Member> members = new ArrayList<>();

    public Event() {
    }
}

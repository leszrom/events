package com.crud.events.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name  = "Eventdate")
    private LocalDateTime date;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Events_Members",
            joinColumns = @JoinColumn(name = "EventID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "MemberID", referencedColumnName = "ID"))
    private List<Member> members = new ArrayList<>();

    public Event() {
    }

    public Event(String name, String description, LocalDateTime date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Event(Long id, String name, String description, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Member> getMembers() {
        return members;
    }
}

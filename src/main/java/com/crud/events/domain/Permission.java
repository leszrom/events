package com.crud.events.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "Role")
    private String role;

    @ManyToMany(mappedBy = "permissions")
    private List<Member> members = new ArrayList<>();

    public Permission() {
    }
}

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
    private Long id;

    @Column(name = "Role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "permissions")
    private List<Member> members = new ArrayList<>();

    public Permission() {
    }

    public Permission(Role role) {
        this.role = role;
    }
}

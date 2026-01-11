package com.lighter.granate.entity;

import jakarta.persistence.*;

import java.util.Set;

@Table(name = "roles")
@Entity
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(nullable = false, unique = true)
    private String title;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String toString() {
        return "Role(id=" + this.getId() + ", title=" + this.getTitle() + ", users=" + this.getUsers() + ")";
    }
}

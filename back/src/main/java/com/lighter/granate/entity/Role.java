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

    public Role(int id, String title, Set<User> users) {
        this.id = id;
        this.title = title;
        this.users = users;
    }
    public Role() {

    }


    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

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

    public static class RoleBuilder {
        private int id;
        private String title;
        private Set<User> users;

        RoleBuilder() {
        }

        public RoleBuilder id(int id) {
            this.id = id;
            return this;
        }

        public RoleBuilder title(String title) {
            this.title = title;
            return this;
        }

        public RoleBuilder users(Set<User> users) {
            this.users = users;
            return this;
        }

        public Role build() {
            return new Role(this.id, this.title, this.users);
        }

        public String toString() {
            return "Role.RoleBuilder(id=" + this.id + ", title=" + this.title + ", users=" + this.users + ")";
        }
    }
}

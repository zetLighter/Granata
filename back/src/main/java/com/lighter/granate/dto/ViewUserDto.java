package com.lighter.granate.dto;

public class ViewUserDto {
    private int id;
    private String username;
    private String email;

    public ViewUserDto(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public ViewUserDto() {
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "ViewUserDto(id=" + this.getId() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ")";
    }
}

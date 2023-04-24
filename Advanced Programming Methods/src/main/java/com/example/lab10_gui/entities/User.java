package com.example.lab10_gui.entities;

import java.util.ArrayList;
import java.util.Objects;

public class User extends Entity<Integer>{
    private final String username;
    private final String last_name;
    private final String first_name;
    private final String password;
    private final ArrayList<User> friends;


    public User(Integer id, String username, String last_name, String first_name, String password) {
        this.setId(id);
        this.username = username;
        this.last_name = last_name;
        this.first_name = first_name;
        this.password = password;
        this.friends=new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(last_name, user.last_name) && Objects.equals(first_name, user.first_name) && Objects.equals(password, user.password) && Objects.equals(friends, user.friends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, last_name, first_name);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", password= "+ password+ '\''+
                '}';
    }
}

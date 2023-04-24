package com.example.lab10_gui.entities;

import java.util.ArrayList;

public class Network {
    private ArrayList<User> users;
    private ArrayList<Friendship> friendships;

    public Network() {
        this.users= new ArrayList<>();
        this.friendships= new ArrayList<>();
    }
}

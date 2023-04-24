package com.example.lab10_gui.graph_algorithms;

import com.example.lab10_gui.entities.User;

public class UserNode{
    private final User user;
    private boolean visited=false;

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public User getUser() {
        return user;
    }

    public UserNode(User user) {
        this.user = user;
        this.visited=false;
    }
}

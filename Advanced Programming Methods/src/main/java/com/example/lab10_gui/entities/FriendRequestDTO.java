package com.example.lab10_gui.entities;

public class FriendRequestDTO {
    private String username;
    private String status;

    public FriendRequestDTO(String username, String status) {
        this.username = username;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }
}

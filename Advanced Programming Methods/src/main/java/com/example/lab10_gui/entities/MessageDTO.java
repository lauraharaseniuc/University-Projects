package com.example.lab10_gui.entities;

public class MessageDTO {
    private String senderUsername;
    private String content;

    public MessageDTO(String senderUsername, String content) {
        this.senderUsername = senderUsername;
        this.content = content;
    }
}

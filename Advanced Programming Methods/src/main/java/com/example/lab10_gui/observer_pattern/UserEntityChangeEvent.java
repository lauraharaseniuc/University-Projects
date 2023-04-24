package com.example.lab10_gui.observer_pattern;

import com.example.lab10_gui.entities.User;

public class UserEntityChangeEvent implements Event{
    private final ChangeEventType eventType;
    private User data;
    private User oldData;

    public UserEntityChangeEvent(ChangeEventType eventType) {
        this.eventType = eventType;
    }

    public UserEntityChangeEvent(ChangeEventType eventType, User data, User oldData) {
        this.eventType = eventType;
        this.data = data;
        this.oldData = oldData;
    }

    public UserEntityChangeEvent(ChangeEventType eventType, User data) {
        this.eventType = eventType;
        this.data = data;
    }

    public ChangeEventType getEventType() {
        return eventType;
    }

    public User getData() {
        return data;
    }

    public User getOldData() {
        return oldData;
    }
}

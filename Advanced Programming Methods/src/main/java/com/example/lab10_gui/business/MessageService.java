package com.example.lab10_gui.business;

import com.example.lab10_gui.entities.Message;
import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.observer_pattern.ChangeEventType;
import com.example.lab10_gui.observer_pattern.Observable;
import com.example.lab10_gui.observer_pattern.Observer;
import com.example.lab10_gui.observer_pattern.UserEntityChangeEvent;
import com.example.lab10_gui.repositories.db_repositories.MessageDbRepository;

import java.util.ArrayList;
import java.util.List;

public class MessageService {
    private final MessageDbRepository messageDbRepository;

    public List<Message> get_all_messages_between_two_users(User sender, User receiver) {
        if (receiver==null) {
            System.out.println("3----------------------------------------------------");
        }
        return this.messageDbRepository.get_all_messages_between_two_users(sender,receiver);
    }
    public void send_message (User sender, User receiver, String message) {
        this.messageDbRepository.send_message(sender.getId(),receiver.getId(),message);
    }

    public MessageService(MessageDbRepository messageDbRepository) {
        this.messageDbRepository = messageDbRepository;
    }
}

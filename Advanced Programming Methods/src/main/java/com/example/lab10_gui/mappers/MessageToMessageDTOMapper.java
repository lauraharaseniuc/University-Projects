package com.example.lab10_gui.mappers;

import com.example.lab10_gui.entities.Message;
import com.example.lab10_gui.entities.MessageDTO;
import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.repositories.db_repositories.UserDbRepository;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageToMessageDTOMapper {
    private User sender;
    private User receiver;
    private UserDbRepository userDbRepository;

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setUserDbRepository(UserDbRepository userDbRepository) {
        this.userDbRepository = userDbRepository;
    }

    private HBox convert (Message message) {
        User sender_user= this.userDbRepository.get_user_on_id(message.getSenderId());
        User receiver_user=this.userDbRepository.get_user_on_id(message.getReceiverId());
        HBox box= new HBox();
        Text text= new Text(sender_user.getUsername()+": "+message.getContent());
        box.getChildren().add(text);
        if (Objects.equals(sender_user.getId(), sender.getId())) {
            box.setAlignment(Pos.BASELINE_RIGHT);
        }
        else if (Objects.equals(sender_user.getId(), receiver.getId())) {
            box.setAlignment(Pos.BASELINE_LEFT);
        }
        return box;
    }
    public List<HBox> convert (List<Message> messages) {
        List<HBox> messageDTOS= new ArrayList<>();
        messages.forEach(message -> {
            messageDTOS.add(convert(message));
        });
        return messageDTOS;
    }
}

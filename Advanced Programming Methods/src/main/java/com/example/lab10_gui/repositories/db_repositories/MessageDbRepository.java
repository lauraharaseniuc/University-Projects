package com.example.lab10_gui.repositories.db_repositories;

import com.example.lab10_gui.entities.Message;
import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.repositories.CrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class MessageDbRepository implements CrudRepository<Integer, Message> {
    private final String url;
    private final String username;
    private final String password;

    public MessageDbRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public List<Message>  get_all_messages_between_two_users (User sender, User receiver) {
        List<Message> messages= new ArrayList<>();
        try {
            if (receiver==null) {
                System.out.println("4----------------------------------------------------");
            }
            String sqlCommand="SELECT * FROM MESSAGES WHERE (sender_id=? and receiver_id=?) OR (sender_id=? and receiver_id=?)  ORDER BY message_id";
            Connection connection= DriverManager.getConnection(this.url,this.username,this.password);
            PreparedStatement statement=connection.prepareStatement(sqlCommand);
            statement.setInt(1,sender.getId());
            statement.setInt(2,receiver.getId());
            statement.setInt(3,receiver.getId());
            statement.setInt(4,sender.getId());

            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()) {
                Integer sender_id=resultSet.getInt("sender_id");
                Integer receiver_id=resultSet.getInt("receiver_id");
                String content=resultSet.getString("message_content");
                messages.add(new Message(sender_id,receiver_id,content));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }

    public void send_message (Integer sender_id, Integer receiver_id, String message) {
        try {
            String sqlCommand="INSERT INTO MESSAGES (sender_id, receiver_id, message_content) VALUES (?,?,?)";
            Connection connection= DriverManager.getConnection(this.url,this.username,this.password);
            PreparedStatement statement=connection.prepareStatement(sqlCommand);
            statement.setInt(1,sender_id);
            statement.setInt(2,receiver_id);
            statement.setString(3,message);

            statement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    @Override
    public Message add(Message entity) {
        return null;
    }

    @Override
    public Message delete(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Message> getAllEntities() {
        return null;
    }
}

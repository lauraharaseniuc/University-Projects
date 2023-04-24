package com.example.lab10_gui.entities;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class UserSearchResult {
    private Integer id;
    private String lastName;
    private String firstName;
    private String username;
    private FriendshipStatus friendshipStatus;
    @FXML
    private HBox buttonBox;
    private Button button1;
    private Button button2;



    public UserSearchResult(Integer id, String lastName, String firstName, String username, FriendshipStatus friendshipStatus) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.friendshipStatus = friendshipStatus;
        this.buttonBox=new HBox();
        if (friendshipStatus==FriendshipStatus.SENT) {
            this.button1 = new Button();
            this.button1.setText("DELETE REQUEST");
            this.buttonBox.getChildren().add(this.button1);
        }
        if (friendshipStatus==FriendshipStatus.NON_EXISTENT) {
            this.button1 = new Button();
            this.button1.setText("ADD");
            this.buttonBox.getChildren().add(this.button1);
        }
        if (friendshipStatus==FriendshipStatus.PENDING) {
            this.button1 = new Button();
            this.button1.setText("ACCEPT");
            this.button2=new Button();
            this.button2.setText("REJECT");
            this.buttonBox.getChildren().add(this.button1);
            this.buttonBox.getChildren().add(this.button2);
        }
        if (friendshipStatus==FriendshipStatus.ACCEPTED) {
            this.button1 = new Button();
            this.button1.setText("REMOVE FRIEND");
            this.buttonBox.getChildren().add(this.button1);
        }
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUsername() {
        return username;
    }

    public HBox getButtonBox() {
        return buttonBox;
    }

    public Button getButton1() {
        return button1;
    }

    public Button getButton2() {
        return button2;
    }

    public FriendshipStatus getFriendshipStatus() {
        return friendshipStatus;
    }

    public Integer getId() {
        return id;
    }
}

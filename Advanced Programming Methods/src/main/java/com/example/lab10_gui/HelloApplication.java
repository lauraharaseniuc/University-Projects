package com.example.lab10_gui;

import com.example.lab10_gui.business.MessageService;
import com.example.lab10_gui.business.UserService;
import com.example.lab10_gui.exceptions.RepositoryError;
import com.example.lab10_gui.repositories.db_repositories.FriendshipDbRepository;
import com.example.lab10_gui.repositories.db_repositories.MessageDbRepository;
import com.example.lab10_gui.repositories.db_repositories.UserDbRepository;
import com.example.lab10_gui.validators.UserValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLogInWindowLoader= new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLogInWindowLoader.load(), 600, 300);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css_styles/log_in_styles.css")).toExternalForm());

        stage.setTitle("Social Network");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        LogInController logInController=fxmlLogInWindowLoader.getController();

//        UserController userController= fxmlLoader.getController();
//
        UserValidator userValidator= new UserValidator();
        String url="jdbc:postgresql://localhost:5432/academic";
        String username="postgres";
        String password="postgres";
        UserDbRepository userDbRepository= new UserDbRepository(url, username, password);
        FriendshipDbRepository friendshipDbRepository = new FriendshipDbRepository(url, username, password);
        MessageDbRepository messageDbRepository= new MessageDbRepository(url,username,password);
        try {
            MessageService messageService=new MessageService(messageDbRepository);
            UserService userService = new UserService(userDbRepository, friendshipDbRepository, messageService,userValidator);
            logInController.setService(userService);
        } catch (RepositoryError re) {
            System.out.println("An error occurred while synchronizing data from user file and friendship databases.\n");
            return;
        }


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
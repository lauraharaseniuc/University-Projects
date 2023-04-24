package com.example.lab10_gui;

import com.example.lab10_gui.business.UserService;
import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.exceptions.RepositoryError;
import com.example.lab10_gui.exceptions.ValidationError;
import com.example.lab10_gui.observer_pattern.Observable;
import com.example.lab10_gui.observer_pattern.Observer;
import com.example.lab10_gui.observer_pattern.UserEntityChangeEvent;
import com.example.lab10_gui.repositories.db_repositories.FriendshipDbRepository;
import com.example.lab10_gui.repositories.db_repositories.UserDbRepository;
import com.example.lab10_gui.validators.UserValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserController implements Observer<UserEntityChangeEvent> {
    private final ObservableList<User> usersModel = FXCollections.observableArrayList();
    @FXML
    private TableView<User> usersTableView;
    @FXML
    private TableColumn<User,String> userUsernameColumn;
    @FXML
    private TableColumn<User, String> userLastNameColumn;
    @FXML
    private TableColumn<User, String> userFirstNameColumn;

    @FXML
    private TextField userUsernameField;
    @FXML
    private TextField userLastNameField;
    @FXML
    private TextField userFirstNameField;
    @FXML
    private TextField userPasswordField;

    private UserService userService;

    private void initializeModel() {
        Iterable<User> users =this.userService.get_all_users();
        List<User> userList= StreamSupport.stream(users.spliterator(), false).toList();
        this.usersModel.setAll(userList);
    }

    public UserController() {
    }

    public void setService (UserService userService) {
        this.userService=userService;
        this.userService.addObserver(this);
        this.initializeModel();
    }

    @FXML
    public void initialize() {
        this.userUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username")); //"username" -> name of the attribute in the object
        this.userLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        this.userFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        //this.userService.get_all_users().forEach(user -> {this.usersTableView.getItems().add(user);});
        usersTableView.setItems(this.usersModel);
    }
    @FXML
    private void onSignInUser(ActionEvent actionEvent) {
        String userUsername = userUsernameField.getText();
        String userLastName= userLastNameField.getText();
        String userFirstName= userFirstNameField.getText();
        String userPassword= userPasswordField.getText();
        try {
            this.userService.add_user(userUsername, userLastName, userFirstName, userPassword);
            //if is present
            this.usersTableView.getItems().add(this.userService.getUserOnUsername(userUsername).get());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ValidationError e) {
            throw new RuntimeException(e);
        } catch (RepositoryError e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onDeleteUser(ActionEvent actionEvent) {
        int index=this.usersTableView.getSelectionModel().getSelectedIndex();
        if (index!=-1) {
            User userToDelete=this.usersTableView.getSelectionModel().getSelectedItem();
            try {
                this.userService.remove_user(userToDelete.getId());
                this.usersTableView.getItems().remove(userToDelete);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ValidationError e) {
                throw new RuntimeException(e);
            } catch (RepositoryError e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(UserEntityChangeEvent userEntityChangeEvent) {
        this.initializeModel();
    }
}
package com.example.lab10_gui;

import com.example.lab10_gui.business.UserService;
import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.exceptions.RepositoryError;
import com.example.lab10_gui.exceptions.ValidationError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LogInController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logInButton;
    @FXML
    private Button closeButton;
    @FXML
    private Label logInErrorMessageLabel;
    private UserService userService;
    public void setService (UserService userService) {
        this.userService=userService;
        this.logInErrorMessageLabel.setText("");
    }


    public void onLogInUser(ActionEvent actionEvent) {
        String username=this.usernameField.getText();
        String password=this.passwordField.getText();
        try {
            User user_logged=this.userService.log_in_user(username, password);

            //open new window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("user-logged-in-view.fxml"));
            Parent root=loader.load();
            SessionController sessionController=loader.getController();
            sessionController.initialize(this.userService, user_logged);
            Scene logged_in_scene=new Scene (root);
            logged_in_scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("css_styles/session_styles.css")).toExternalForm());
            //Stage main_stage=(Stage)this.logInButton.getScene().getWindow();

            Stage newStage= new Stage();
            newStage.setScene(logged_in_scene);
            newStage.show();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } catch (ValidationError | RepositoryError e) {

            this.logInErrorMessageLabel.setText("");
            this.logInErrorMessageLabel.setText(e.getMessage());
        }
    }

    public void exitLogInWindow(ActionEvent actionEvent) {
        Stage stage= (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }
}

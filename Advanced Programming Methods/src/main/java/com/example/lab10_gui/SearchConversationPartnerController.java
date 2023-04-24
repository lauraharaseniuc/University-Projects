package com.example.lab10_gui;

import com.example.lab10_gui.business.UserService;
import com.example.lab10_gui.entities.Friendship;
import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.mappers.FriendshipToUserMapper;
import com.example.lab10_gui.observer_pattern.Observer;
import com.example.lab10_gui.observer_pattern.UserEntityChangeEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class SearchConversationPartnerController implements Observer<UserEntityChangeEvent> {
    private User userLoggedIn;
    private UserService userService;
    private final FriendshipToUserMapper friendshipToUserMapper=new FriendshipToUserMapper();
    private SessionController sessionController;

    @FXML
    private Button startConversationButton;
    @FXML
    private ListView<User> friendsListView;

    @FXML
    public void initialize() {
        this.friendsListView.setCellFactory(param->new ListCell<User>(){
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getUsername() == null) {
                    setText(null);
                } else {
                    setText(item.getUsername());
                }
            }
        });
    }

    public void initializeModel() {
        this.friendsListView.setId("searchConversationPartnerListView");
        List<Friendship> friendships = this.userService.get_all_friends_belonging_to_a_user(this.userLoggedIn);
        List<User> friends= friendshipToUserMapper.convert(friendships);
        this.friendsListView.getItems().setAll(friends);
    }

    public void initialize_everything(UserService userService, User user, SessionController sessionController) {
        this.userService = userService;
        this.sessionController=sessionController;
        this.userService.addObserver(this);
        this.userLoggedIn = user;
        this.friendshipToUserMapper.setUserDbRepository(this.userService.getUserDbRepository());

        this.initializeModel();
    }

    @Override
    public void update(UserEntityChangeEvent userEntityChangeEvent) {
        this.initializeModel();
    }

    public void onClickStartNewConversation(ActionEvent actionEvent) {
        User selected_user=this.friendsListView.getSelectionModel().getSelectedItem();
        this.sessionController.setConversationPartner(selected_user);
        Stage stage= (Stage) this.startConversationButton.getScene().getWindow();
        stage.close();
    }
}
